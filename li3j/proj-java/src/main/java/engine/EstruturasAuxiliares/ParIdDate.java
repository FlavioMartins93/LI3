package engine.EstruturasAuxiliares;

import java.lang.Object;
import java.time.LocalDate;

/**
 * Classe que define um par contendo o Id e data de um post
 * 
 * @author Flavio Martins, Mario Santos, Pedro Costa 
 */

public class ParIdDate implements Comparable<ParIdDate>
{
    // variáveis de instância
    private long id;                         //id do post
    private LocalDate date;                  //data de criação do post

    /**
     * Construtores para objetos da classe ParIdDate
     */
    public ParIdDate()
    {
        id = 0;
        date = null;
    }
    
    public ParIdDate(long id, LocalDate date)
    {
        this.id = id;
        this.date = date;
    }
    
    public ParIdDate(ParIdDate par)
    {
        this.id = par.getId();
        this.date = par.getDate();
    }

    // Metodo getters
    public long getId()
    {
        return this.id;
    }
    
    public LocalDate getDate()
    {
        return this.date;
    }
    
    // Metodos setters
    public void setId(long id)
    {
        this.id = id;
    }
    
    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public ParIdDate clone() 
    {
        return new ParIdDate(this);
    }

    @Override  
    public int compareTo(ParIdDate comp) {
        return -this.date.compareTo(comp.getDate());
    }
}
