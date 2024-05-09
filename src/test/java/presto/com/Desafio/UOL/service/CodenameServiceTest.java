package presto.com.Desafio.UOL.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CodenameServiceTest {
    @InjectMocks
    CodenameService codenameService;

    @Mock
    RestTemplate restTemplate;

    @Mock
    ObjectMapper objectMapper;

    @Mock
    private DocumentBuilder builder;

    @Mock
    Environment env;

    CodenameServiceTest() throws ParserConfigurationException {
    }

    @Nested
    class initializeJson{

        @Test
        void shouldFillVingadoresList() throws JsonProcessingException {
    //      Assert
            String jsonResponse = "{\"vingadores\": [{\"codinome\": \"Hulk\"}, {\"codinome\": \"Thor\"}]}";

            when(restTemplate.getForObject(env.getProperty("vingadores.url"), String.class))
                    .thenReturn(jsonResponse);

            JsonNode node = new ObjectMapper().readTree(jsonResponse);

            doReturn(node).when(objectMapper).readTree(jsonResponse);

            List<String> list = codenameService.getVingadores();

    //      Act
            codenameService.initializeJson();


    //      Assert
            assertEquals(2, list.size());
            assertEquals("Hulk", list.get(0));
            assertEquals("Thor", list.get(1));
        }

        @Test
        void shouldThrowExceptionWhenUrlReturnIsNotAJson() throws JsonProcessingException {
            //      Assert
            String wrongString = "<>String<>";

            // Act & Assert
            assertThrows(Exception.class, () -> new ObjectMapper().readTree(wrongString));
        }
    }

    @Nested
    class initializeXML{
        @Test
        void shouldFillLigaDaJusticaList() throws Exception{
//          Arrange
            String xmlContent = "<root><codinome>Flash</codinome><codinome>Batman</codinome></root>";

            InputSource inputSource = new InputSource(new StringReader(xmlContent));

            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(inputSource);

            when(builder.parse(env.getProperty("ligadajustica.url"))).thenReturn(document);

//          Act
            codenameService.initializeXML();

            List<String> list = codenameService.getLigadajustica();

//          Assert
            assertEquals(2, list.size());
            assertEquals("Flash", list.get(0));
            assertEquals("Batman", list.get(1));
        }

        @Test
        void shouldThrowExceptionWhenUrlReturnIsNotXML() throws Exception{
//          Arrange
            //      Assert
            String xmlContent = "<>String<>";

            InputSource inputSource = new InputSource(new StringReader(xmlContent));
            DocumentBuilder newBuild = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder();

            // Act & Assert
            assertThrows(Exception.class, () -> newBuild.parse(inputSource));
        }
    }
}