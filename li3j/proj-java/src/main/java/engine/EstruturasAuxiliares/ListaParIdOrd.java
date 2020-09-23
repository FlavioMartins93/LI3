package engine.EstruturasAuxiliares;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
* Nesta classe é implementada uma lista de ids de utilizadores ordenada por reputaçao ou total de posts
* 
* @author Flavio Martins, Mario Santos, Pedro Costa 
*/

public class ListaParIdOrd
{
	// variáveis de instância
	private List<ParIdOrd> lista = new ArrayList<ParIdOrd>();        /*Lista de pares (Id,Ord) em que Ord corresponde ao 
																					criterio de ordenaçao, reputação ou total de posts */
	/** Construtor para objetos da classe ListaUtilizadores	*/
	public void ListaParIdOrd()
	{
		lista = new ArrayList<ParIdOrd>();
	}

	/* Metodo getter */
	public List<ParIdOrd> getListaParIdOrd() {
		return this.lista;
	}
	//adiciona um par a lista
	public void inserirNovoParIdOrd(ParIdOrd par)
	{
		this.lista.add(par);
	}

	//ordena a lista
	public void sortListaParIdOrd()
	{
	   Collections.sort(this.lista);
	}

	public void clear() {
		this.lista.clear();
	}
}
