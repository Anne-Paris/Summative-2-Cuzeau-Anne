package com.company.Summative2CuzeauAnne.controller;


import com.company.Summative2CuzeauAnne.model.Publisher;
import com.company.Summative2CuzeauAnne.repository.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PublisherController.class)
public class PublisherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublisherRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    private Publisher publisher;
    private String publisherJson;
    private List<Publisher> allPublisher = new ArrayList<>();
    private String allPublisherJson;

    @Before
    public void setup() throws Exception {
        publisher = new Publisher();
        publisher.setId(1);
        publisher.setName("Publisher1");
        publisher.setState("NY");
        publisher.setStreet("One NYC st.");
        publisher.setPostalCode("45678");
        publisher.setCity("NYC");
        publisher.setPhone("678-900-0000");
        publisher.setEmail("publisher@qwerty.com");


        publisherJson = mapper.writeValueAsString(publisher);

        Publisher publisher2 = new Publisher();
        publisher2.setName("Publisher2");
        publisher2.setState("CA");
        publisher2.setStreet("One CA st.");
        publisher2.setPostalCode("00001");
        publisher2.setCity("LA");
        publisher2.setPhone("123-444-0000");
        publisher2.setEmail("publisher2@qwerty.com");

        allPublisher.add(publisher);
        allPublisher.add(publisher2);

        allPublisherJson = mapper.writeValueAsString(allPublisher);
    }

    @Test
    public void shouldCreateNewPublisherOnPostRequest() throws Exception {
        Publisher inputPub = new Publisher();
        inputPub.setId(3);
        inputPub.setName("Publisher3");
        inputPub.setState("CA");
        inputPub.setStreet("One CA st.");
        inputPub.setPostalCode("00001");
        inputPub.setCity("LA");
        inputPub.setPhone("123-444-0000");
        inputPub.setEmail("publisher2@qwerty.com");

        String inputJson = mapper.writeValueAsString(inputPub);

        doReturn(publisher).when(repo).save(inputPub);

        mockMvc.perform(
                        post("/publishers")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(publisherJson));

    }

    @Test
    public void shouldReturnPublisherById() throws Exception {
        doReturn(Optional.of(publisher)).when(repo).findById(1);

        ResultActions result = mockMvc.perform(
                        get("/publishers/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(publisherJson))
                );
    }

    @Test
    public void shouldBStatusOkForNonExistentPublisherId() throws Exception {
        doReturn(Optional.empty()).when(repo).findById(25);

        mockMvc.perform(
                        get("/publishers/25"))
                .andExpect(status().isOk()
                );

    }


    @Test
    public void shouldReturnAllPublishers() throws Exception {
        doReturn(allPublisher).when(repo).findAll();

        mockMvc.perform(
                        get("/publishers"))
                .andExpect(status().isOk())
                .andExpect(content().json(allPublisherJson)
                );
    }

    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(
                        put("/publishers")
                                .content(publisherJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/publishers/1")).andExpect(status().isNoContent());
    }



}
