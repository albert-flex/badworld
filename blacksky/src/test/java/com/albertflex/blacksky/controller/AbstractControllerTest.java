/*

 */
package com.albertflex.blacksky.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "albert",password = "moeyui0624")
public class AbstractControllerTest {

    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup(){
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }

    protected <T> T getResult(String url, Class<T> resultCls) throws Exception {
        MvcResult result = mockMvc.perform(get(url)).
                andExpect(status().isOk()).andReturn();
        return objectMapper.readValue(result.getResponse().
                getContentAsString(StandardCharsets.UTF_8), resultCls);
    }

    protected <T> List<T> getListResult(String url, Class<T> resultCls) throws Exception {
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class,resultCls);
        MvcResult result = mockMvc.perform(get(url)).
                andExpect(status().isOk()).andReturn();
        return objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), listType);
    }
    
    protected <T> T postResult(String url, Object body, Class<T> resultCls) throws Exception {
        MvcResult result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body))).
                andExpect(status().isOk()).andReturn();
        return objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), resultCls);
    }

    protected <T> T deleteResult(String url, Class<T> resultCls) throws Exception {
        MvcResult result = mockMvc.perform(delete(url)).
                andExpect(status().isOk()).andReturn();
        return objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), resultCls);
    }

    protected <T> T putResult(String url, Object body, Class<T> resultCls) throws Exception {
        MvcResult result = mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body))).
                andExpect(status().isOk()).andReturn();
        return objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), resultCls);
    }
}
