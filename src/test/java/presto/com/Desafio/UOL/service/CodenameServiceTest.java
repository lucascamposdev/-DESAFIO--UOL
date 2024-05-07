package presto.com.Desafio.UOL.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CodenameServiceTest {

    @Mock
    RestTemplate restTemplate;

    @Mock
    ObjectMapper objectMapper;

    @Mock
    Environment env;

    @InjectMocks
    CodenameService codenameService;

    @Nested
    class initializeJson{

        @Test
        void shouldFillVingadoresList() throws JsonProcessingException {
    //      Assert
            String jsonResponse = "{\"vingadores\": [{\"codinome\": \"Hulk\"}, {\"codinome\": \"Thor\"}]}";

            when(restTemplate.getForObject(env.getProperty("vingadores.url"), String.class)).thenReturn(jsonResponse);

            JsonNode node = new ObjectMapper().readTree(jsonResponse);

            doReturn(node).when(objectMapper).readTree(jsonResponse);

    //      Act
            codenameService.initializeJson();

            List<String> list = codenameService.getVingadores();

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