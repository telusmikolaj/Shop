package pl.waw.great.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.waw.great.shop.model.dto.MessageDto;
import pl.waw.great.shop.repository.MessageRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageRepository messageRepository;

    private MessageDto messageDto;

    private static final String TITLE = "title";
    private static final String TEXT = "txt";
    private static final String CITY = "city";
    private static final String EMAIL = "email";

    @BeforeEach
    void setUp() {
        this.messageDto = new MessageDto(TITLE, TEXT, CITY, EMAIL);
    }

    @AfterEach
    void tearDown() {
        this.messageRepository.deleteAll();
    }

    @Test
    void create() throws Exception {
        String messageDtoAsJson = objectMapper.writeValueAsString(this.messageDto);

        MvcResult result = sendRequest(MockMvcRequestBuilders.post("/message")
                                .content(messageDtoAsJson)
                                .contentType(MediaType.APPLICATION_JSON), HttpStatus.OK);

        MessageDto messageDto = objectMapper.readValue(result.getResponse().getContentAsString(), MessageDto.class);

        assertNotNull(messageDto);
        assertEquals(TITLE, messageDto.getTitle());
    }

    private MvcResult sendRequest(RequestBuilder request, HttpStatus expectedStatus) throws Exception {
        return mockMvc.perform(request)
                .andExpect(status().is(expectedStatus.value()))
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
    }
}