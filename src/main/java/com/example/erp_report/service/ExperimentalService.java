package com.example.erp_report.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.erp_report.model.Experimental;
import com.example.erp_report.repository.ExperimentalRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ExperimentalService {

    @Autowired
    private ExperimentalRepository experimentalRepository;

    public Experimental saveExperimentalData(Experimental experimental) {
        return experimentalRepository.save(experimental);
    }

    public JsonNode serviceTest() throws IOException {
        String data = """
                      [  {
                            "name": "a",
                            "science": 40,
                            "maths": 56,
                            "tamil": 87,
                            "social": 65,
                            "english": 78
                        }
                ]
                        """;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(data);
        System.out.println("service hhhhhhhhhhhhh");
        // if(true){
        //     throw  new IOException();
        // }
        // throw{
        // }
        return root;
    }

}
