package engine.BaseDeDados;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import Utilizadores.*;
import EstruturasAuxiliares.*;

/**
 * Nesta classe é implementado o parse do ficheiro "Users.xml"
 * 
 * @author Flávio Martins, Mário Santos, Pedro Costa
 */

public class ParseUtilizadores extends DefaultHandler {

    //hash a preencher com os utilizadores durante o parse
    private HashUtilizadores hashUtil = new HashUtilizadores();
    private Utilizador util = null;
    /* Lista com pares do tipo (utilizador Id, Reputação)*/
    private ListaParIdOrd listaUlizadores = new ListaParIdOrd();
    private ParIdOrd idRep = null;

    //getter da hashUtilizadores após o parse
    public HashUtilizadores getUtilHash() {
        return this.hashUtil;
    }

    //getter da lista de utilizadores após o parse
    public ListaParIdOrd getListaUtilRep(){
    	return this.listaUlizadores;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("row")) {

            //Inicializar Utilizador e set dos atributos
            util = new Utilizador();
            String temp = attributes.getValue("Id");
            util.setId((long) (Integer.parseInt(temp)));
            temp = attributes.getValue("DisplayName");
            util.setDisplayName(temp);
            temp = attributes.getValue("AboutMe");
            util.setAboutMe(temp);
            temp = attributes.getValue("Reputation");
            util.setReputation((long) (Integer.parseInt(temp)));

            idRep = new ParIdOrd();                              //inicializar par (id, reputação)
            idRep.setId(util.getId());
            idRep.setOrd(util.getReputation());
            listaUlizadores.inserirNovoParIdOrd(idRep);        //inserir par (id, reputação) na lista de utilizadores ordenados por reputaçao

            try {
                hashUtil.inserirNovoUtilizador(util);          //inserir utilizador na hash
            } catch (Exception e) {}
        }      
    }
}
