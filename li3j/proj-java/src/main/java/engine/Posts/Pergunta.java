package engine.Posts;

import java.time.LocalDate;

/**
 * Nesta classe são apresentadas as variaveis de instacia e metodos de instacia relativos a uma pergunta 
 * 
 * @authors Flávio Martins, Mário Santos, Pedro Costa
 */

public class Pergunta extends Post
{
    private String title;              //titulo da pergunta
    private String tags;               //tags da pergunta
   

    /** Construtor para objetos da classe post */

    public Pergunta()
    {
    	super();
    	title = "";
    	tags = "";
    }

    public Pergunta(long id, long owner, LocalDate creationDate, String title, String tags)
    {
	super(id, owner, creationDate);
        this.title = title;
        this.tags = tags;

    }

    public Pergunta(Pergunta p)
    {
    	super(p.getId(), p.getOwner(), p.getCreationDate());
    	this.title = p.getTitle();
    	this.tags = p.getTags();
    }

    /** Métodos getters **/

    public String getTitle()
    {
        return this.title;
    }
    
    public String getTags()
    {
        return this.tags;
    }

    /** Métodos setters **/
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public void setTags(String tags)
    {
        this.tags = tags;
    }

    /** clone - equals **/

    public Pergunta clone()
    {
        return new Pergunta(this);
    }
    
    public boolean equals(Object o)
    {
        if (this==o) return true;
        if (o==null || (this.getClass()!=o.getClass())) return false;
        Pergunta p = (Pergunta) o;
        return (super.equals(p) &&
                this.title.equals(p.getTitle()) &&
                this.tags.equals(p.getTags()));
    }
}
