package engine.Posts;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Nesta classe é implementada uma hashmap que irá guardar as respostas 
 * 
 * @authors Flávio Martins, Mário Santos, Pedro Costa
 */

public class HashRespostas
{
	
    private HashMap<Long,Resposta> hashRespostas;        //hashmap de respostas utilizando o seu id como chave

    /**
     * Construtor para objetos da classe HashTableRespostas
     */
    public HashRespostas()
    {
        hashRespostas = new HashMap<Long,Resposta>();
    }

    public HashRespostas(HashRespostas hp) 
    {
        this.hashRespostas = hp.getRespostas();
    }
    
    //devolve todas as respostas na hash map
    public HashMap<Long,Resposta> getRespostas()
    {
        HashMap<Long,Resposta> nova = new HashMap<Long,Resposta>();
        
        for(Map.Entry<Long,Resposta> hp : hashRespostas.entrySet()) {
            Resposta p = hp.getValue();
            nova.put(hp.getKey(), p.clone());
        }
        return nova;
    }
    
    //inserir uma resposta na hash table
    public void inserirNovaResposta(Resposta p)
    {
        hashRespostas.put(p.getId(), p);
    }
    
    //remove uma resposta da hash table
    public void removerResposta(Resposta p)
    {
        hashRespostas.remove(p.getId());
    }
    
    //verifica se uma resposta existe na hash table
    public boolean existeResposta(long id)
    {
        return (hashRespostas.containsKey(id));
    }
    
    //devolve uma copia da resposta da hash table
    public Resposta getResposta(long id)
    {
        Resposta p = hashRespostas.get(id).clone();
        return p;
    }

    public HashRespostas clone() {

        return (new HashRespostas(this));
    }

    public void clear() {
        this.hashRespostas.clear();
    }

}
