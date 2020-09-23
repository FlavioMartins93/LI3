package engine.EstruturasAuxiliares;

import java.lang.Object;
import static java.lang.Math.toIntExact;

/**
 * Classe que define um par contendo o Id e reputaçao/total de posts dum utilizador
 * 
 * @author Flavio Martins, Mario Santos, Pedro Costa 
 */

public class ParIdOrd implements Comparable<ParIdOrd>
{
    // variáveis de instância
    private long id;                         //id do utilizador
    private long ord;                        //long correspondente ao criterio de ordenação

    /**
     * Construtor para objetos da classe UtilParIdRep
     */
    public ParIdOrd()
    {
        id = 0;
        ord = 0;
    }
    
    public ParIdOrd(long id, long ord)
    {
        this.id = id;
        this.ord = ord;
    }
    
    public ParIdOrd(ParIdOrd par)
    {
        this.id = par.getId();
        this.ord = par.getOrd();
    }

    // Metodos getters
    public long getId()
    {
        return this.id;
    }
    
    public long getOrd()
    {
        return this.ord;
    }
    
    // Metodos setters
    public void setId(long id)
    {
        this.id = id;
    }
    
    public void setOrd(long ord)
    {
        this.ord = ord;
    }

    public ParIdOrd clone()
    {
        return new ParIdOrd(this);
    }

    @Override  
    public int compareTo(ParIdOrd comp) {
        long compareOrd = (((ParIdOrd)comp).getOrd());
        return (int) (compareOrd - this.ord);
    }
}
