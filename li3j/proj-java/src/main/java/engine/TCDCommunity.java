package engine;


import BaseDeDados.*;
import Utilizadores.*;
import Posts.*;
import EstruturasAuxiliares.*;
import common.Pair;
import li3.TADCommunity;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.Collection;
import java.util.Collections;


public class TCDCommunity implements TADCommunity {

    private BaseDeDados bd;
    
    public void init() {
       	this.bd = new BaseDeDados();
    }
    

    public void load(String dumpPath) {
    	if (this.bd==null) {
    		this.init();
    	} else {
    		this.clear();
    	}

    	this.bd.loadBD(dumpPath);
    }

    // Query 1
    public Pair<String,String> infoFromPost(long id) {
		Resposta resposta = null;
		Pergunta pergunta;
		Utilizador utilizador;

		if(bd.getHashRespostas().existeResposta(id)) resposta = bd.getHashRespostas().getResposta(id);   //verifica se é resposta
		if(resposta!=null) {
			pergunta = bd.getHashPerguntas().getPergunta(resposta.getParentid());                //caso o id seja duma resposta vai buscar a pergunta correspondente
		} else {
			pergunta = bd.getHashPerguntas().getPergunta(id);                                    //caso seja id duma pergunta vai buscar a pergunta
		}
		utilizador = bd.getHashUtilizadores().getUtilizador(pergunta.getOwner());                //get utilizador que fez a pergunta!
		String owner = utilizador.getDisplayName();                                      
		String title = pergunta.getTitle();

        return new Pair<>(title, owner);
    }

    // Query 2
    public List<Long> topMostActive(int N) {
    	ListaParIdOrd listaUtilTotalPosts = bd.getListaUtilTotalPosts();                 //lista de todos utilizadores ordenados por total de posts!
    	List<Long> res = new ArrayList<Long>();                                          //lista a ser preenchida com os N mais activos dentro do ciclo for
		int i = 0;
		for(ParIdOrd par : listaUtilTotalPosts.getListaParIdOrd())
		{
			res.add(par.getId());
			i++;
			if (i==N) break;
		}
        return res;
    }

    // Query 3
    public Pair<Long,Long> totalPosts(LocalDate begin, LocalDate end) {
    	/* TreeMaps de perguntas e datas que utilizam como key a data e guardam uma lista de ids das perguntas/respostas nessa data */
		TreeMapPosts treePergsDate = bd.getTreePergsDate();
		TreeMapPosts treeRespsDate = bd.getTreeRespsDate();
		LocalDate dateTemp = begin;
		int totalResp = 0;
		int totalPerg = 0;
		/* Ciclo que corre as datas no intervalo, como o value é uma lista o seu tamanho é o total de perguntas/respostas do dia,
		basta entao adicionar o seu tamnho ao total de respostas/perguntas */
		while(dateTemp.isBefore(end.plusDays(1))) {
			totalResp += treeRespsDate.getListaIds(dateTemp).size();
			totalPerg += treePergsDate.getListaIds(dateTemp).size();
			dateTemp = dateTemp.plusDays(1);
		}

        return new Pair<>((long) totalPerg,(long) totalResp);
    }

    // Query 4
    public List<Long> questionsWithTag(String tag, LocalDate begin, LocalDate end) {
		TreeMapPosts treePergsDate = bd.getTreePergsDate();
		LocalDate dateTemp = end;
		HashPerguntas hashPerguntas = bd.getHashPerguntas();
		List<Long> listaIds;
		List<Long> res = new ArrayList<Long>();
		Pergunta pergunta;

		while (dateTemp.isAfter(begin.plusDays(-1))) {
			listaIds = treePergsDate.getListaIds(dateTemp);
			for (long tempid : listaIds) {
				pergunta = hashPerguntas.getPergunta(tempid);
				if( pergunta.getTags().contains(tag)) res.add(tempid);
			}
			dateTemp=dateTemp.plusDays(-1);
		}
        return res;
    }

    // Query 5
    public Pair<String, List<Long>> getUserInfo(long id) {
    	HashUtilizadores hashUtilizadores = bd.getHashUtilizadores();
		Utilizador utilizador = hashUtilizadores.getUtilizador(id);
		String aboutMe = utilizador.getAboutMe();

		ListaParIdDate listaPosts = utilizador.getListaPosts();

		List<Long> listRes = listaPosts.getLastN(10);    //devolve os ultimos N posts da junção das duas listas

        return new Pair<>(aboutMe,listRes);
    }

    // Query 6
    public List<Long> mostVotedAnswers(int N, LocalDate begin, LocalDate end) {
    	TreeMapPosts treeRespsDate = bd.getTreeRespsDate();
		HashRespostas hashRespostas = bd.getHashRespostas();
		Resposta resposta;
		LocalDate dateTemp = begin;
		List<Long> listaIds;
		ListaParIdOrd retList = new ListaParIdOrd();
		ParIdOrd par;
		while(dateTemp.isBefore(end.plusDays(1))) {
			listaIds = treeRespsDate.getListaIds(dateTemp);
			for(long tempid : listaIds) {
				resposta = hashRespostas.getResposta(tempid);	
				par = new ParIdOrd();
				par.setId(resposta.getId());
				par.setOrd(resposta.getScore());
				retList.inserirNovoParIdOrd(par);
			}
			dateTemp=dateTemp.plusDays(1);
		}
		retList.sortListaParIdOrd();
		List<ParIdOrd> returnList = retList.getListaParIdOrd();
		int i=0;
		List<Long> ret = new ArrayList<Long>();
		for(ParIdOrd parTemp : returnList) {
			if (i==N) break;
			i++;
			ret.add(parTemp.getId());
		}
        return ret;
    }

    // auxiliar query 7 conta as respostas duma pergunta num determinado intervalo de tempo!
    private long contaRespostas(Pergunta p, LocalDate begin, LocalDate end) {
    	HashRespostasPorPergunta hashListaRespostas = bd.getHashRespostasPorPergunta();
    	HashRespostas hashRespostas = bd.getHashRespostas();
    	Resposta resposta;
    	if (!hashListaRespostas.containsKey(p.getId())) return 0;
    	List<Long> listaIds = hashListaRespostas.getListaIds(p.getId());
    	long ret = 0;
    	for (long tempid : listaIds) {
    		resposta = hashRespostas.getResposta(tempid);
    		if(resposta.getCreationDate().isBefore(end.plusDays(1)) && resposta.getCreationDate().isAfter(begin.plusDays(-1))) ret++;
    		// ret++; sem verificar a data das respostas --> resultado final == aos de referência!
    	}
    	return ret;
    }

    // Query 7
    public List<Long> mostAnsweredQuestions(int N, LocalDate begin, LocalDate end) {
		TreeMapPosts treePergsDate = bd.getTreePergsDate();
		HashPerguntas hashPerguntas = bd.getHashPerguntas();
		Pergunta pergunta;
		LocalDate dateTemp = begin;
		List<Long> listaIds;
		ListaParIdOrd retList = new ListaParIdOrd();
		ParIdOrd par;
		long numResps;
		while(dateTemp.isBefore(end.plusDays(1))) {             // sobre resultados apontamentos na auxiliar contaRespostas!
			listaIds = treePergsDate.getListaIds(dateTemp);
			for(long tempid : listaIds) {
				pergunta = hashPerguntas.getPergunta(tempid);
				numResps = contaRespostas(pergunta, begin, end);
				par = new ParIdOrd();
				par.setId(pergunta.getId());
				par.setOrd(numResps);
				retList.inserirNovoParIdOrd(par);
			}
			dateTemp = dateTemp.plusDays(1);
		}
		retList.sortListaParIdOrd();
		List<ParIdOrd> returnList = retList.getListaParIdOrd();
		int i=0;
		List<Long> ret = new ArrayList<Long>();
		for(ParIdOrd parTemp : returnList) {
			if (i==N) break;
			i++;
			ret.add(parTemp.getId());
		}
        return ret;
    }

    // Query 8
    public List<Long> containsWord(int N, String word) {
    	HashPerguntas hashPerguntas = bd.getHashPerguntas();
    	int i = 0;
    	TreeMapPosts tree =  bd.getTreePergsDate();                /* TreeMap contendo uma lista de ids de perguntas em cada dia */
		LocalDate date = tree.getLastKey();                        /* Data inicia como a data do post mais recente */
		List<Long> ret = new ArrayList<Long>();                    /* Lista de ids onde irão ser colocados os ids das perguntas contendo a 'word' no titulo */
		List<Long> listaIds;                                       /* Lista que irá conter os Ids das perguntas de cada dia */
		Pergunta perg;
		/* Ciclo corre até obter N perguntas
		   Para cada dia vai buscar a lista de ids das perguntas do dia
		   Percorre depois as perguntas do dia e verifica se contem a palavra, caso contenha adiciona a lista ret */
		while (i<N) {
			listaIds = tree.getListaIds(date);
			for (long pergId : listaIds) {
				if(i==N) return ret;
				perg = hashPerguntas.getPergunta(pergId);
				if (perg.getTitle().contains(word)) {
					ret.add(pergId);
					i++;
				}
			}
			date = date.plusDays(-1);
		}

		return ret;
    }

    // auxiliar de query 9 verifica se um utilizador respondeu a uma pergunta
    public boolean participa(long pergid, long userid) {
    	HashRespostasPorPergunta hashRespostasPorPergunta = bd.getHashRespostasPorPergunta();
    	if(!hashRespostasPorPergunta.containsKey(pergid)) return false;
    	List<Long> listaIds = hashRespostasPorPergunta.getListaIds(pergid);
    	HashRespostas hashRespostas = bd.getHashRespostas();
    	Resposta resposta;
    	for (long tempid : listaIds) {
    		resposta = hashRespostas.getResposta(tempid);
    		if (resposta.getOwner() == userid) return true;
    	}
    	return false;
    }

    // Query 9
    public List<Long> bothParticipated(int N, long id1, long id2) {
    	ListaParIdDate listaResultado = new ListaParIdDate();
    	HashUtilizadores hashUtilizadores = bd.getHashUtilizadores();
    	ListaParIdDate listaPosts;
    	Utilizador utilizador1 = hashUtilizadores.getUtilizador(id1);
    	Utilizador utilizador2 = hashUtilizadores.getUtilizador(id2);

    	/* Vai buscar a lista de posts de cada utilizador, em seguida, para poder iterar vai buscar como List<>
    	   Durante o ciclo, para cada post que corresponda a uma pergunta pergunta verifica se o outro utilizador participa, 
    	   usando a funçao auxiliar participa, caso participe adiciona a uma lista resultado que irá ser ordenada depois*/
    	listaPosts = utilizador1.getListaPosts();
    	List<ParIdDate> listaPostsDate = listaPosts.getListaParIdDate();
    	for (ParIdDate par : listaPostsDate) {
    		if (bd.getHashPerguntas().existePergunta(par.getId())) {
    			if(participa(par.getId(), id2)) listaResultado.inserirNovoParIdDate(par);
    		}
    	}

    	listaPosts = utilizador2.getListaPosts();
    	listaPostsDate = listaPosts.getListaParIdDate();
    	for (ParIdDate par : listaPostsDate) {
    		if (bd.getHashPerguntas().existePergunta(par.getId())) {
    			if(participa(par.getId(), id1)) listaResultado.inserirNovoParIdDate(par);
    		}
    	}

    	List<Long> ret = listaResultado.getIdsOrdenados();  /* Vai buscar a lista criada de forma ordenada por data */
        List<Long> retr = new ArrayList<Long>();

        /* Garantir que apenas devolve os N primeiros posts em que ambos participam, caso existam mais */
		int i = 0;
		for(Long idactual : ret) {
			if (i==N) break;
			i++;
			retr.add(idactual);
		}

        return retr;
    }

    // Query 10
    public long betterAnswer(long id) {
    	HashRespostasPorPergunta hashListaRespostas = bd.getHashRespostasPorPergunta();    /* HashMap contendo a lista de respostas de cada pergunta */
    	if (!hashListaRespostas.containsKey(id)) return -1;                                /* Caso a pergunta não tenho qualquer resposta return -1*/
    	List<Long> listaIds = hashListaRespostas.getListaIds(id);                          /* Get lista de ids das respostas da pergunta */
    	HashRespostas hashRespostas = bd.getHashRespostas().clone();
    	HashUtilizadores hashUtilizadores = bd.getHashUtilizadores();

    	/* Obter o id a primeira resposta da lista 
    	   Em segudia vamos buscar a resposta na hashMap que contem as respostas e calculamos o 'score' dessa resposta
    	   Guardando o seu id como better e o seu score como o score da melhor actual */
    	long respid = listaIds.get(0);
    	Resposta resposta = hashRespostas.getResposta(respid);
    	Utilizador utilizador = hashUtilizadores.getUtilizador(resposta.getOwner());
    	long better = listaIds.get(0);
    	double betterScore = (resposta.getScore() * 0.65) + (resposta.getComCount() * 0.1) + (utilizador.getReputation());
    	/* O ciclo for percorre a lista de ids correspondentes às respostas da pergunta
    	   caso a resposta correspondente tenha um melhor 'score' do que o actual substitui o score e o id na variavel better */
		for(Long tempid : listaIds) {
			resposta = hashRespostas.getResposta(tempid);
    		utilizador = hashUtilizadores.getUtilizador(resposta.getOwner());
    		if ( betterScore < ((resposta.getScore() * 0.65) + (resposta.getComCount() * 0.1) + (utilizador.getReputation())) ) {
    			betterScore = (resposta.getScore() * 0.65) + (resposta.getComCount() * 0.1) + (utilizador.getReputation());
    			better = tempid;
    		}
		}
        return better;
    }

    /* Auxiliar de query 11, percorre a lista de perguntas do utilizador e actualiza a hash de tags utilizadas*/
    public HashMap<String, Long> tagsUsedUser(Utilizador util, LocalDate begin, LocalDate end, HashMap<String, Long> hashTagsUsed) {
    	HashPerguntas hashPerguntas = bd.getHashPerguntas();
    	HashRespostas hashRespostas = bd.getHashRespostas();
		List<ParIdDate> listaPosts = util.getListaPosts().getListaParIdDate(); /* Lista de posts, na forma(Id, Date) do utilizador */
		Pergunta pergunta = new Pergunta();
		Resposta resposta = new Resposta();
		String tags, tag;                                                      /* tags irá guardar todas as tags, tag sera para ir buscar as tags individualmente*/
		String delimiter = "<>";
		long count;


		/* percorrer os posts */
		for (ParIdDate par : listaPosts) {
			/* verificar se a data do post está no intervalo pretendido */
			if(par.getDate().isBefore(end.plusDays(1)) && par.getDate().isAfter(begin.plusDays(-1))) {
				if (hashRespostas.existeResposta(par.getId())) {
					resposta = hashRespostas.getResposta(par.getId());
					if (hashPerguntas.existePergunta(resposta.getParentid())) pergunta = hashPerguntas.getPergunta(resposta.getParentid());
				} else {
					pergunta = hashPerguntas.getPergunta(par.getId());
				}
				//if  ( ((hashRespostas.existeResposta(par.getId())) && (hashPerguntas.existePergunta(resposta.getParentid()))) ||
				/* Apenas iremos verificar as tag das perguntas pois contabilizando as respostas obtinhamos resultados diferentes dos de referencia 
				   Iremos mesmo assim deixar a resolução para o caso em que se contabilizava tambem as tas das respostas*/
				if(	(!(hashRespostas.existeResposta(par.getId()))) ) {
					tags = pergunta.getTags();
					/* StringTokenizer para poder dividir a String <tag1><tag2>... nas tags individuais */
					StringTokenizer token = new StringTokenizer(tags, delimiter);
					while(token.hasMoreTokens()) {
						count = (long) 0;
						tag = token.nextToken();
						/* verificar se não é a primeira vez que a tag actual é utilizada
						   caso não seja, ir buscar a contagem actual                    */
						if (hashTagsUsed.containsKey(tag)) {
							count = hashTagsUsed.get(tag);
						}
						count = count + 1;
						hashTagsUsed.put(tag,count);
					}
				}
			}
		}
		return hashTagsUsed;
    }

    // Query 11 
    public List<Long> mostUsedBestRep(int N, LocalDate begin, LocalDate end) {
    	int i = 0;                                                              /* Numero de utilizadores ja contabilizados */
    	Utilizador utilActual = new Utilizador();
    	List<ParIdOrd> listaUtilRep = bd.getListaUtilRep().getListaParIdOrd();  /* Lista de Ids de utilizadores já ordenada por reputação */

    	HashMap<String,Long> hashTagsUsed = new HashMap<String,Long>();         /* key(tag como string), value (numero de utilizações) */
    	ParIdOrd tagCount;                                                      /* Par do tipo (identificador de tag, numero de utilizações) */


    	for (ParIdOrd par : listaUtilRep) {
    		if (i==N) break;                        /* sair do ciclo quando percorridos os N utilizadores */
    		i++;
    		utilActual = bd.getHashUtilizadores().getUtilizador(par.getId()).clone();
    		hashTagsUsed = tagsUsedUser(utilActual.clone(), begin, end, hashTagsUsed);   /* Na funçao auxiliar é que se vai pegar na lista de perguntas do utilizador
    																				     e adicionar as suas tags na hashMap contendo todas as tags e nº de utilizações*/
    	}

    	/* Criação duma lista do tipo ListaParIdOrd, que ira conter pares do tipo (identificadar da tag, nº de utilizações) de modo a ordenar as tags por utilização */
    	ListaParIdOrd listaTagsOrdenada = new ListaParIdOrd();
        for (Map.Entry entrys : hashTagsUsed.entrySet()) {
			tagCount = new ParIdOrd();
			tagCount.setId((long) (bd.getHashTags().get(entrys.getKey())));
			tagCount.setOrd((long) (entrys.getValue()));
			listaTagsOrdenada.inserirNovoParIdOrd(tagCount);
        }
        /* A ordenação apenas é feita no final do ciclo */
        listaTagsOrdenada.sortListaParIdOrd();

        List<ParIdOrd> listaTags = listaTagsOrdenada.getListaParIdOrd();
        List<Long> ret = new ArrayList<Long>();
        i = 0;

        /* Ciclo em que percorremos os N primeiros elementos da lista, colocando-os assim na Lista de retorno */
		for(ParIdOrd par : listaTags)
		{
			if(i==N) break;
			ret.add(par.getId());
			i++;
		}

        return ret;
    }

    public void clear(){
    	bd.clear();
    }
}
