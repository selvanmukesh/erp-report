package com.example.erp_report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ErpReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpReportApplication.class, args);
	}

}
