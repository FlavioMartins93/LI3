package engine.Utilizadores;


import EstruturasAuxiliares.ListaParIdDate;
import EstruturasAuxiliares.ParIdDate;

/**
 * Nesta classe são apresentadas as variaveis de instacia e metodos de instacia relativos a um Utilizador 
 * 
 * @authors Flávio Martins, Mário Santos, Pedro Costa
 * @version 1.0
 */

public class Utilizador
{
    // variáveis de instância
    private long id;                          //id do utilizador
    private String displayName;               //display name do utilizador
    private String aboutMe;                   //descriçao sobre si do próprio utilizador
    private long reputation;                  //reputação do utilizador
    private long totalPosts;                  //contagem do total de posts (respostas e perguntas) do utilizador
    private ListaParIdDate listaPosts;        //lista de par(id,date) dos posts do utilizador;

    /** Construtores para objetos da classe Utilizador */

    public Utilizador()
    {
        id = 0;
        displayName = "";
        aboutMe = "";
        reputation = 0;
        totalPosts = 0;
        listaPosts = new ListaParIdDate();

    }

    public Utilizador (long id, String displayName, String aboutMe, long reputation, long totalPosts, ListaParIdDate listaPosts)
    {
        this.id = id;
        this.displayName = displayName;
        this.aboutMe = aboutMe;
        this.reputation = reputation;
        this.totalPosts = totalPosts;
        this.listaPosts = listaPosts;
    }

    public Utilizador (Utilizador u)
    {
        this.id = u.getId();
        this.displayName = u.getDisplayName();
        this.aboutMe = u.getAboutMe();
        this.reputation = u.getReputation();
        this.totalPosts = u.getTotalPosts();
        this.listaPosts = u.getListaPosts();
    }

    /** Métodos getters **/

    public long getId() 
    {
        return this.id;
    }

    public String getDisplayName() 
    {
        return this.displayName;
    }
    
    public String getAboutMe()
    {
        return this.aboutMe;
    }

    public long getReputation()
    {
        return this.reputation;
    }

    public long getTotalPosts()
    {
        return this.totalPosts;
    }

    public ListaParIdDate getListaPosts()
    {
        return this.listaPosts;
    }

    /** Métodos setters */

    public void setId(long id)
    {
        this.id = id;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public void setAboutMe(String aboutMe)
    {
        this.aboutMe = aboutMe;
    }

    public void setReputation(long reputation)
    {
        this.reputation = reputation;
    }

    public void setTotalPosts(long totalPosts)
    {
        this.totalPosts = totalPosts;
    }

    public void setListaPosts(ListaParIdDate listaPosts)
    {
        this.listaPosts = listaPosts;
    }

    //aumenta em 1 o total de posts do utilizador
    public void incTotalPosts()                     
    {
        this.totalPosts += 1;
    }

    //adiciona pergunta
    public void adicionaPost(ParIdDate post)
    {
        this.listaPosts.inserirNovoParIdDate(post);
    }

    /** clone - equals **/

    public Utilizador clone()
    {
        return new Utilizador(this);
    }
    
    public boolean equals(Object o)
    {
        if(this==o){return true;}
        if(o==null || (this.getClass() != o.getClass())){return false;}

        Utilizador u = (Utilizador) o;

        return	(this.displayName.equals(u.getDisplayName()) &&
				 this.aboutMe.equals(u.getDisplayName()) &&
				 this.id == u.getId() &&
				 this.reputation == u.getReputation() &&
				 this.totalPosts == u.getTotalPosts());		
    }

}
