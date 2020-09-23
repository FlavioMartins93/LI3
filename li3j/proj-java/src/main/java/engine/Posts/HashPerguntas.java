package engine.Posts;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Nesta classe é implementada uma hashmap que irá guardar as perguntas 
 * 
 * @authors Flávio Martins, Mário Santos, Pedro Costa
 */

public class HashPerguntas
{
	
    private HashMap<Long,Pergunta> hashPerguntas;        //hashmap de perguntas utilizando o seu id como chave

    /**
     * Construtor para objetos da classe HashTablePerguntas
     */
    public HashPerguntas()
    {
        hashPerguntas = new HashMap<Long,Pergunta>();
    }

    public HashPerguntas(HashPerguntas hp) 
    {
        this.hashPerguntas = hp.getPerguntas();
    }
    
    //devolve todas as perguntas na hash map
    public HashMap<Long,Pergunta> getPerguntas()
    {
        HashMap<Long,Pergunta> nova = new HashMap<Long,Pergunta>();
        
        for(Map.Entry<Long,Pergunta> hp : hashPerguntas.entrySet()) {
            Pergunta p = hp.getValue();
            nova.put(hp.getKey(), p.clone());
        }
        return nova;
    }
    
    //inserir uma Pergunta na hash table
    public void inserirNovaPergunta(Pergunta p)
    {
	   hashPerguntas.put(p.getId(), p);
    }
    
    //remove uma pergunta da hash table
    public void removerPergunta(Pergunta p)
    {
        hashPerguntas.remove(p.getId());
    }
    
    //verifica se uma pergunta existe na hash table
    public boolean existePergunta(long id)
    {
        return (hashPerguntas.containsKey(id));
    }
    
    //devolve o pergunta da hash table corresponde ao id(chave)
    public Pergunta getPergunta(long id)
    {
        Pergunta p = hashPerguntas.get(id).clone();
        return p;
    }

    public HashPerguntas clone() {
        return (new HashPerguntas(this));
    }

    public void clear() {
        this.hashPerguntas.clear();
    }
}
