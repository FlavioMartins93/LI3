package engine.Utilizadores;

import java.util.HashMap;
import java.util.Map;
import EstruturasAuxiliares.ListaParIdOrd;
import EstruturasAuxiliares.ParIdOrd;

/**
 * Nesta classe é implementada uma hash map que irá guardar os utilizadores 
 * 
 * @authors Flávio Martins, Mário Santos, Pedro Costa
 * @version 1.0
 */

public class HashUtilizadores
{
    private HashMap<Long,Utilizador> utilizadores;  //HashMap de utilizadores utilizando o seu id como chave

    /** Construtores para objetos da classe HashUtilizadores */
    public HashUtilizadores(){
        utilizadores = new HashMap<Long,Utilizador>();
    }

    public HashUtilizadores(HashUtilizadores hu){
        this.utilizadores = hu.getUtilizadores();
    }

    /** Métodos getters **/
    public HashMap<Long,Utilizador> getUtilizadores(){
        HashMap<Long,Utilizador> nova = new HashMap<Long,Utilizador>();

        for(Map.Entry<Long,Utilizador> hu : utilizadores.entrySet()) {
            Utilizador u = hu.getValue();
            nova.put(hu.getKey(),u.clone());
        }
        return nova;
    }

    public ListaParIdOrd getListaUtilPosts() {

        ListaParIdOrd listaUtilPosts = new ListaParIdOrd();

        for(Map.Entry<Long,Utilizador> hu : utilizadores.entrySet()) {
            Utilizador u = hu.getValue();
            ParIdOrd par = new ParIdOrd();
            par.setId(u.getId());
            par.setOrd(u.getTotalPosts());
            listaUtilPosts.inserirNovoParIdOrd(par);

        }
        return listaUtilPosts;
    }

    //insere um novo utilizador na hash table
    public void inserirNovoUtilizador(Utilizador u)
	{
        utilizadores.put(u.getId(),u.clone());
    }

    //remove um utilizador da hash table
    public void removerUtilizador(long id){
        utilizadores.remove(id);
    }

    //verifica se existe uma chave(id de utilizador) na hash table
    public boolean existeUtilizador(long id){
        return(utilizadores.containsKey(id));
    }

    //devolve um utilizador correspondente a um id de utilizador
    public Utilizador getUtilizador(long id){
        Utilizador u = utilizadores.get(id);
        return u;
    }

    public void clear() {
        this.utilizadores.clear();
    }
}
