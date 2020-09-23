package engine.BaseDeDados;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import Posts.*;

/**
 * Nesta classe é implementado o parse do ficheiro "Votes.xml"
 * 
 * @author Flávio Martins, Mário Santos, Pedro Costa
 */

public class ParseVotes extends DefaultHandler {

    //listas de perguntas e respostas
    private HashRespostas hashRespostas = new HashRespostas();
    private Resposta resp = null;

    //set hash respostas já inicializada anteriormente
    public void setHashRespostas(HashRespostas hr) {
        this.hashRespostas = new HashRespostas(hr);
    }

    //getters listas de Utilizadores e respostas
    public HashRespostas getHashRespostas() {
        return this.hashRespostas;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("row")) {

            //inicializar pergunta e sets
            resp = new Resposta();
            long id = (Long.parseLong(attributes.getValue("PostId")));
            
            if (this.hashRespostas.existeResposta(id)) {
                resp = hashRespostas.getResposta(id);
                hashRespostas.removerResposta(resp);
                resp.incVoteCount();
                this.hashRespostas.inserirNovaResposta(resp);
            }
        }
    }
}