package engine.Posts;

import java.time.LocalDate;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Nesta classe é implementada uma hashmap que irá guardar uma lista de Ids das respostas correspondes a cada pergunta(utilizando o id da pergunta como key)
 * 
 * @authors Flávio Martins, Mário Santos, Pedro Costa
 */

public class HashRespostasPorPergunta 
{
	HashMap<Long,List<Long>> hash;  

    /**
     * Construtor para objetos da classe HashTableRespostas
     */
	public HashRespostasPorPergunta(){
		this.hash = new HashMap<Long,List<Long>>();
	}

    /* Devolve a lista de ids correspondentes a uma pergunta     */
	public List<Long> getListaIds(Long key) {
		return this.hash.get(key);
	}

	/* Verifica se contem um key correspondente ao id duma pergunta, ou seja, se essa pergunta tem alguma resposta */
	public boolean containsKey(Long key) {
		return this.hash.containsKey(key);
	}

	/* Adiciona a lista de respostas correspondente a um id de pergunta */
	public void adicionaListaIds (Long key, List<Long> listaIds) {
		this.hash.put(key, listaIds);
	}

	public void clear() {
		this.hash.clear();
	}
}
