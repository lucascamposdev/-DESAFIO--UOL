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
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    private DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

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

            when(restTemplate.getForObject("dummy.url", String.class))
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
}