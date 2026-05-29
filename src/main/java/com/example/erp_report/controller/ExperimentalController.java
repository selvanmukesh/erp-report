package com.example.erp_report.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.erp_report.model.Experimental;
import com.example.erp_report.service.ExperimentalService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/test")
public class ExperimentalController {
    private ExperimentalService experimentalService;

    @GetMapping
    public String getMethodName() {
        return "hello";
    }

    @PostMapping("path")
    public Experimental saveExperimentalData(@RequestBody Experimental experimental) {

        return experimentalService.saveExperimentalData(experimental);
    }

}
