package engine.EstruturasAuxiliares;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;

/**
* Esta classe implementa uma lista de ids de posts ordenada por data
* 
* @author Flavio Martins, Mario Santos, Pedro Costa 
*/

public class ListaParIdDate
{
	// variáveis de instância
	private List<ParIdDate> lista = new ArrayList<ParIdDate>();        /*Lista de pares (Id,date) 

	/** Construtor para objetos da classe ListaUtilizadores	*/
	public void ListaParIdDate()
	{
		lista = new ArrayList<ParIdDate>();
	}

	//getter
	public List<ParIdDate> getListaParIdDate() {
		return this.lista;
	}
	//adiciona um par a lista
	public void inserirNovoParIdDate(ParIdDate par)
	{
		this.lista.add(par);
	}

	//verifica se existem posts em determinada data
	public boolean containsKey(LocalDate key) {
		return this.lista.contains(key);
	}

	//ordena a lista
	public void sortListaParIdDate()
	{
	   Collections.sort(this.lista);
	}

	/* Devolve toda a lista de ids na forma List<Long> ordenados */
	public List<Long> getIdsOrdenados() {
		Collections.sort(this.lista);
		List<Long> res = new ArrayList<Long>();
		for(ParIdDate par : this.lista) {
			res.add(par.getId());
		}
		return res;
	}

	/* Devolve os primeiros N ids da lista*/
	public List<Long> getLastN(int N){
		Collections.sort(this.lista);
		List<Long> res = new ArrayList<Long>();
		int i = 0;
		for(ParIdDate par : this.lista){
			if (i==N) break;
			i++;
			res.add(par.getId());
		}
		return res;
	}

	public void clear() {
		this.lista.clear();
	}
}
