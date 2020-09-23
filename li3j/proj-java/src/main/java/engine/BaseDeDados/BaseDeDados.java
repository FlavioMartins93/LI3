package engine.BaseDeDados;

import Utilizadores.*;
import Posts.*;
import EstruturasAuxiliares.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


/**
 * Nesta classe é implementada a base de dados do projecto
 * 
 * @author Flávio Martins, Mário Santos, Pedro Costa
 */

public class BaseDeDados {

    private HashUtilizadores hashUtilizadores;                     /* hashMap dos utilizadores */
    private HashPerguntas hashPerguntas;                           /* hashMap das perguntas */
    private HashRespostas hashRespostas;                           /* hashMap das respostas*/
    private HashRespostasPorPergunta hashRespostasPorPergunta;     /* hashMap de uma lista com os ids das respostas de cada pergunta */
    private ListaParIdOrd listaUtilRep;                            /* lista de ids de utilizador ordenada por reputação */
    private ListaParIdOrd listaUtilTotPosts;                       /* lista de ids de utilizador ordenada por total de posts */
    private TreeMapPosts treePergsDate;                            /* treeMap com a data como key e uma lista de ids das perguntas de cada dia*/
    private TreeMapPosts treeRespsDate;                            /* treeMap com a data como key e uma lista de ids das respostas de cada dia*/
    private HashMap hashTags;                                      /* hashMap de tags e respectivo identificador */


	/** Construtor para objetos da classe BaseDeDados */
    public BaseDeDados() 
    {
        this.hashUtilizadores = new HashUtilizadores();
        this.hashPerguntas = new HashPerguntas();
        this.hashRespostas = new HashRespostas();
        this.hashRespostasPorPergunta = new HashRespostasPorPergunta();
        this.listaUtilRep = new ListaParIdOrd();
        this.listaUtilTotPosts = new ListaParIdOrd();
        this.treePergsDate = new TreeMapPosts();
        this.treeRespsDate = new TreeMapPosts();
        this.hashTags = new HashMap<String,Long>();
    }

    /** Métodos getters **/
    public HashUtilizadores getHashUtilizadores() { return this.hashUtilizadores;}
    public HashPerguntas getHashPerguntas() { return this.hashPerguntas;}
    public HashRespostas getHashRespostas() { return this.hashRespostas;}
    public HashRespostasPorPergunta getHashRespostasPorPergunta() { return this.hashRespostasPorPergunta;}
    public ListaParIdOrd getListaUtilRep() { return this.listaUtilRep;}
    public ListaParIdOrd getListaUtilTotalPosts() { return this.listaUtilTotPosts;}
    public TreeMapPosts getTreePergsDate() { return this.treePergsDate;}
    public TreeMapPosts getTreeRespsDate() { return this.treeRespsDate;}
    public HashMap getHashTags() { return this.hashTags;}

    public void loadBD(String dump_path) {
        
        this.Parsefiles(dump_path);

        /* A criação e ordenação da lista de utilizadores por total de posts apenas pode ser feito no final do parse dos posts,
           Pelo que é realizada após o parse de todos ficheiros, a criação da lista de pares(utilizador Id, total de posts) está na clase hashUtilizadores e é ordenada em seguida*/
        this.listaUtilTotPosts = this.hashUtilizadores.getListaUtilPosts();
        this.listaUtilTotPosts.sortListaParIdOrd();
    }

    /* Utilizando o dump_path como argumento, irá criar as string de path para os ficheiros, Users.xml, Posts.xml, Votes.xml e Tags.xml,
       Fazendo o parse de cada ficheiro de forma ordenada pois existem dependencias entre eles.
       o parse do ficheiro Posts.xml herda a lista de utilizadores, previamente preenchida no parse do ficheiro Users.xml 
       O parse do ficheiro Votes.xml herda a lista de respostas, preciamente preenchida no parse do ficheiro Posts.xml */
    public void Parsefiles(String dump_path) {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            ParseUtilizadores handler = new ParseUtilizadores();
            String pathU = (dump_path + "Users.xml");
            saxParser.parse(new File(pathU), handler);
            this.hashUtilizadores = handler.getUtilHash();
            this.listaUtilRep = handler.getListaUtilRep();
            this.listaUtilRep.sortListaParIdOrd();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            ParsePosts handler = new ParsePosts();
            handler.setHashUtil(this.hashUtilizadores);
            String pathP = (dump_path + "Posts.xml");
            saxParser.parse(new File(pathP), handler);
            this.hashPerguntas = handler.getHashPerg();
            this.hashRespostas = handler.getHashResp();
            this.hashUtilizadores = handler.getHashUtil();
            this.hashRespostasPorPergunta = handler.getHashRespostasPorPergunta();
            this.treeRespsDate  = handler.getTreeRespsDate();
            this.treePergsDate = handler.getTreePergsDate();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            ParseVotes handler = new ParseVotes();
            handler.setHashRespostas(this.hashRespostas);
            String pathV = (dump_path + "Votes.xml");
            saxParser.parse(new File(pathV), handler);
            this.hashRespostas = handler.getHashRespostas();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            ParseTags handler = new ParseTags();
            String pathT = (dump_path + "Tags.xml");
            saxParser.parse(new File(pathT), handler);
            this.hashTags = handler.getHashTags();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        this.hashUtilizadores.clear();
        this.hashPerguntas.clear();
        this.hashRespostas.clear();
        this.hashRespostasPorPergunta.clear();
        this.listaUtilRep.clear();
        this.listaUtilTotPosts.clear();
        this.treePergsDate.clear();
        this.treeRespsDate.clear();
        this.hashTags.clear();
    }
}
