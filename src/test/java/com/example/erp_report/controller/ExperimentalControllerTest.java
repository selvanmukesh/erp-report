package com.example.erp_report.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.example.erp_report.repository.ExperimentalRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ExperimentalControllerTest {

    @Mock
    private ExperimentalRepository experimentalRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUser() throws Exception {

        // mockMvc.perform(get("/test"))
        // .andDo(print())
        // .andExpect(status().isOk())
        // .andExpect(content().string("hello"));
        String response = mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("1 ---> " + response);
    }
}