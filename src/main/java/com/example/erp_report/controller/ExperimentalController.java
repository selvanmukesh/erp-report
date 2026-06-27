package com.example.erp_report.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.erp_report.model.Experimental;
import com.example.erp_report.service.ExperimentalService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/test")
public class ExperimentalController {
    private ExperimentalService experimentalService;

    @GetMapping
    public ResponseEntity<String> getMethodName() {
        // return new ResponseEntity<String>("Hello",HttpStatus.OK);
        return ResponseEntity.accepted().contentType(MediaType.TEXT_PLAIN).body("ok");
    }

    @PostMapping("path")
    public Experimental saveExperimentalData(@RequestBody Experimental experimental) {
        return experimentalService.saveExperimentalData(experimental);
    }

}
