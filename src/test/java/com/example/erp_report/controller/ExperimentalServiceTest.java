package com.example.erp_report.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import com.example.erp_report.service.ExperimentalService;

public class ExperimentalServiceTest {

    private final ExperimentalService experimentalService =
            new ExperimentalService();

    @Test
    void serviceTest_shouldParseJsonSuccessfully() {
        // String serviceTestRest=experimentalService.serviceTest();
        assertDoesNotThrow(() -> experimentalService.serviceTest());
    }
}
