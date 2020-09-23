package engine.BaseDeDados;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.HashMap;

/**
 * Nesta classe é implementado o parse do ficheiro "Tags.xml"
 * 
 * @author Flávio Martins, Mário Santos, Pedro Costa
 */

public class ParseTags extends DefaultHandler {

    private HashMap hashTags = new HashMap<String,Long>(); /* hashMap com a tag como key e o seu identificador como value */

    //getter hashTags
    public HashMap<String,Long> getHashTags() {
        return this.hashTags;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("row")) {
            long id = (Long.parseLong(attributes.getValue("Id")));
            String tag = attributes.getValue("TagName");
            this.hashTags.put(tag,id);
        }
    }
}