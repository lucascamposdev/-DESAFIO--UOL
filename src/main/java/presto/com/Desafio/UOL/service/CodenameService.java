package presto.com.Desafio.UOL.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CodenameService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @Autowired
    private ObjectMapper objectMapper;

    private List<String> vingadores = new ArrayList<>();

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

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
