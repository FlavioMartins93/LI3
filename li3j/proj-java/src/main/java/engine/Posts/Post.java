package engine.Posts;

import java.time.LocalDate;

/**
 * Nesta classe abstracta são apresentadas as variaveis de instacia e metodos de instacia relativos a um Post 
 * 
 * @authors Flávio Martins, Mário Santos, Pedro Costa
 */

public abstract class Post
{
	private long id;
	private long owner;
	private LocalDate creationDate;

    /** Construtor para objetos da classe post */

    public Post()
    {
        id = 0;
        owner = 0;
        creationDate = null;
    }

    public Post(long id, long owner, LocalDate creationDate)
    {
        this.id = id;
        this.owner = owner;
		this.creationDate = creationDate;
    }

    public Post(Post p)
    {
        this.id = p.getId();
        this.owner = p.getOwner();
        this.creationDate = p.getCreationDate();
    }

        /** Métodos getters **/

    public long getId(){
        return this.id;
    }


    public long getOwner()
    {
        return this.owner;
    }

    public LocalDate getCreationDate()
    {
	return this.creationDate;
    }  

    /** Métodos setters **/

    public void setId(long id)
    {
        this.id = id;
    }

    public void setOwner(long owner)
    {
        this.owner = owner;
    }

    public void setCreationDate(String date)
    {
	this.creationDate = LocalDate.parse(date);
    }

    /** clone - equals **/
    
    public abstract Post clone();

    public boolean equals(Object o)
    {
        if (this==o) return true;
        if (o==null || (this.getClass()!=o.getClass())) return false;
        Post p = (Post) o;
        return (this.id==p.getId() &&
                this.owner==p.getOwner() &&
                this.creationDate.equals(p.getCreationDate()));
    }
}
