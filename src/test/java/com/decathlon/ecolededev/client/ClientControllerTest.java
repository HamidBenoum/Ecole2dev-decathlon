package com.decathlon.ecolededev.client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClientService clientService;


    @Before
    public void setup() throws Exception {
        //client trouvé
        when(clientService.getOne(1L)).thenReturn(new Client());
        //client non trouvé
        when(clientService.getOne(2L)).thenThrow(new EntityNotFoundException());
    }

    @Test
    public void getClient_should_return_http_200_when_client_is_found() throws Exception {
        mvc.perform(get("/client/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getClient_should_return_http_400_when_client_is_not_found() throws Exception {
        mvc.perform(get("/client/2"))
                .andExpect(status().isNotFound());
    }

}