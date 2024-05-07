package presto.com.Desafio.UOL.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("LombokGetterMayBeUsed")
@Service
public class CodenameService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @Autowired
    private ObjectMapper objectMapper;

    private List<String> vingadores = new ArrayList<>();
    private List<String> ligadajustica = new ArrayList<>();

    public List<String> getVingadores() {
        return vingadores;
    }

    public List<String> getLigadajustica() {
        return ligadajustica;
    }

    @PostConstruct
    public void initializeJson(){
        try{
            String res = restTemplate.getForObject(env.getProperty("vingadores.url"), String.class);

            JsonNode node = objectMapper
                    .readTree(res)
                    .path("vingadores");

            for (JsonNode codename : node){
                vingadores.add(codename.path("codinome").asText());
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void initializeXML(){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(env.getProperty("ligadajustica.url"));

            NodeList codinamesList = document.getElementsByTagName("codinome");

            for (int i = 0; i < codinamesList.getLength(); i++) {
                Element codinameElement = (Element) codinamesList.item(i);
                String codiname = codinameElement.getTextContent();
                this.ligadajustica.add(codiname);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public <T> boolean hasAvailableCodenames(List<T> list){
        return list != null && !list.isEmpty();
    }
}
