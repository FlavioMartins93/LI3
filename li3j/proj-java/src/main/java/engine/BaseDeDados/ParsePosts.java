package engine.BaseDeDados;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import Utilizadores.*;
import Posts.*;
import EstruturasAuxiliares.*;

/**
 * Nesta classe é implementado o parse do ficheiro "Posts.xml"
 * 
 * @author Flávio Martins, Mário Santos, Pedro Costa
 */

public class ParsePosts extends DefaultHandler {

    //listas de perguntas, respostas e utilizadores
    private HashPerguntas hashPerg = new HashPerguntas();
    private Pergunta perg = null;
    private HashRespostas hashResp = new HashRespostas();
    private Resposta resp = null;
    private HashRespostasPorPergunta respsPorPergunta = new HashRespostasPorPergunta();
    private HashUtilizadores hashUtil;
    private Utilizador util = null;
    private TreeMapPosts treeRespsDate = new TreeMapPosts();
    private TreeMapPosts treePergsDate = new TreeMapPosts();
    private long owner;
    private List<Long> listaIds;
    private ParIdDate parIdDate;

    //set lista de utilizador já inicializada anteriormente
    public void setHashUtil(HashUtilizadores hu) {
        hashUtil = new HashUtilizadores(hu);
    }

    //getters listas de Utilizadores, perguntas e respostas
    public HashPerguntas getHashPerg() {
        return this.hashPerg;
    }
    public HashRespostas getHashResp() {
        return this.hashResp;
    }
    public HashUtilizadores getHashUtil() {
        return this.hashUtil;
    }
    public HashRespostasPorPergunta getHashRespostasPorPergunta(){
        return this.respsPorPergunta;
    }
    public TreeMapPosts getTreeRespsDate() {
        return this.treeRespsDate;
    }
    public TreeMapPosts getTreePergsDate() {
        return this.treePergsDate;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("row")) {

            if(attributes.getValue("PostTypeId").equals("1")) {
                
                //inicializar pergunta e sets
                perg = new Pergunta();

                String temp = attributes.getValue("Id");
                perg.setId(Long.parseLong(temp));
                temp = attributes.getValue("OwnerUserId");
                perg.setOwner(Long.parseLong(temp));
                owner = Long.parseLong(temp);
                temp = attributes.getValue("Title");
                perg.setTitle(temp);
                temp = attributes.getValue("Tags");
                perg.setTags(temp);
                temp = attributes.getValue("CreationDate");
                StringTokenizer st = new StringTokenizer(temp, "T");
                String data = (String) st.nextElement();
                perg.setCreationDate(data);

                this.hashPerg.inserirNovaPergunta(perg);

                
                //if (this.hashUtil.existeUtilizador(owner)) {
                    util = this.hashUtil.getUtilizador(owner).clone();
                    util.incTotalPosts();
                    parIdDate = new ParIdDate();
                    parIdDate.setDate(perg.getCreationDate());
                    parIdDate.setId(perg.getId());
                    util.adicionaPost(parIdDate);
                    this.hashUtil.removerUtilizador(owner);
                    this.hashUtil.inserirNovoUtilizador(util);
                //}

                if (this.treePergsDate.containsKey(perg.getCreationDate())) {
                    this.listaIds = this.treePergsDate.getListaIds(perg.getCreationDate());
                } else {
                    this.listaIds = new ArrayList<Long>();
                }
                this.listaIds.add(perg.getId());
                this.treePergsDate.adicionaListaIds(perg.getCreationDate(), this.listaIds);
            }

            if (attributes.getValue("PostTypeId").equals("2")) {

                //inicializar resposta e sets
                resp = new Resposta();
                String temp = attributes.getValue("Id");
                resp.setId(Long.parseLong(temp));
                temp = attributes.getValue("OwnerUserId");
                resp.setOwner(Long.parseLong(temp));
                owner = Long.parseLong(temp);
                temp = attributes.getValue("Score");
                resp.setScore(Long.parseLong(temp));
                temp = attributes.getValue("ParentId");
                resp.setParentid(Long.parseLong(temp));
                temp = attributes.getValue("CommentCount");
                resp.setComCount(Long.parseLong(temp));
                temp = attributes.getValue("CreationDate");
                StringTokenizer st = new StringTokenizer(temp, "T");
                String data = (String) st.nextElement();
                resp.setCreationDate(data);
		

                this.hashResp.inserirNovaResposta(resp);

                
               // if (this.hashUtil.existeUtilizador(owner)) {
                    util = this.hashUtil.getUtilizador(owner).clone();
                    util.incTotalPosts();
                    parIdDate = new ParIdDate();
                    parIdDate.setDate(resp.getCreationDate());
                    parIdDate.setId(resp.getId());
                    util.adicionaPost(parIdDate);
                    this.hashUtil.removerUtilizador(owner);
                    this.hashUtil.inserirNovoUtilizador(util);
                //}

                if (this.treeRespsDate.containsKey(resp.getCreationDate())) {
                    this.listaIds = this.treeRespsDate.getListaIds(resp.getCreationDate());
                } else {
                    this.listaIds = new ArrayList<Long>();
                }
                this.listaIds.add(resp.getId());
                this.treeRespsDate.adicionaListaIds(resp.getCreationDate(), this.listaIds);

                if (this.respsPorPergunta.containsKey(resp.getParentid())) {
                    this.listaIds = this.respsPorPergunta.getListaIds(resp.getParentid());
                } else {
                    this.listaIds = new ArrayList<Long>();
                }
                this.listaIds.add(resp.getId());
                this.respsPorPergunta.adicionaListaIds(resp.getParentid(), this.listaIds);
            }
        }   
    }
}
