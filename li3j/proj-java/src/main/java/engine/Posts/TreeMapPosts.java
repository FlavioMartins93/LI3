package engine.Posts;

import java.time.LocalDate;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map;
import java.util.List;

/**
 * Nesta classe é implementada uma treeMap que irá uma lista de ids de post correspondentes a uma data, ou seja os posts de cada dia
 * 
 * @authors Flávio Martins, Mário Santos, Pedro Costa
 */


public class TreeMapPosts 
{
	TreeMap<LocalDate,List<Long>> tree;

    /** Construtor para objetos da classe TreeMapPosts */
	public TreeMapPosts(){
		this.tree = new TreeMap<LocalDate,List<Long>>();
	}

    /** Métodos getters **/
	public List<Long> getListaIds(LocalDate key) {
		return this.tree.get(key);
	}

	/* Verifica se na treeMap existe uma data, ou seja se existem posts nesse dia */
	public boolean containsKey(LocalDate key) {
		return this.tree.containsKey(key);
	}

	/* Adiciona uma lista de ids correspondentes aos posts desse dia */
	public void adicionaListaIds(LocalDate key, List<Long> listaIds) {
		this.tree.put(key, listaIds);
	}

	/* Devolve a ultima chava na TreeMap, ou seja, o dia do post mais recente */
	public LocalDate getLastKey() {
		return this.tree.lastKey();
	}

	public void clear() {
		this.tree.clear();
	}

}
