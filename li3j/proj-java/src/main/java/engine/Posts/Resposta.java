package engine.Posts;

import java.time.LocalDate;

/**
 * Nesta classe são apresentadas as variaveis de instacia e metodos de instacia relativos a uma resposta 
 * 
 * @authors Flávio Martins, Mário Santos, Pedro Costa
 * @version 1.0
 */

public class Resposta extends Post
{
    private long parentid;                //id da resposta
    private long score;                   //score da respostas(Upvotes - DownVotes)
    private long comCount;                //contagem de comentarios recebidos pela resposta
    private long voteCount;               //contagem de votos recebidos pela resposta
   

    /** Construtor para objetos da classe resposta */

    public Resposta()
    {
    	super();
    	parentid = 0;
    	score = 0;
    	comCount = 0;
    	voteCount = 0;
    }

    public Resposta(long id, long owner, LocalDate creationDate, long parentid, long score, long comCount, long voteCount)
    {
		super(id, owner, creationDate);
		this.parentid = parentid;
		this.score = score;
		this.comCount = comCount;
		this.voteCount = voteCount;
    }

    public Resposta(Resposta r)
    {
    	super(r.getId(), r.getOwner(), r.getCreationDate());
    	this.parentid = r.getParentid();
    	this.score = r.getScore();
    	this.comCount = r.getComCount();
    	this.voteCount = r.getVoteCount();
    }

    /** Métodos getters **/

	public long getParentid()
	{
		return this.parentid;
	}

	public long getScore()
	{ 
		return this.score;
	}

	public long getComCount()
	{
		return this.comCount;
	}

	public long getVoteCount()
	{
		return this.voteCount;
	}

    /** Métodos setters **/

	public void setParentid(long pid)
	{
		this.parentid = pid;
	}

	public void setScore(long score)
	{ 
		this.score = score;
	}

	public void setComCount(long comCount)
	{
		this.comCount = comCount;
	}

	public void setVoteCount(long voteCount)
	{
		this.voteCount = voteCount;
	}

    public void incVoteCount()
    {
        this.voteCount += 1;
    }
    
    /** clone - equals **/

    public Resposta clone()
    {
        return new Resposta(this);
    }
    
    public boolean equals(Object o)
    {
        if (this==o) return true;
        if (o==null || (this.getClass()!=o.getClass())) return false;
        Resposta r = (Resposta) o;
        return (super.equals(r) &&
        		this.parentid == r.getParentid() &&
        		this.score == r.getScore() &&
        		this.comCount == r.getComCount() && 
        		this.voteCount == r.getVoteCount());
    }
}
