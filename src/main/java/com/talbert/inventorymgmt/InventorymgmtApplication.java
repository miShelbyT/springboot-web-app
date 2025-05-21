package com.talbert.inventorymgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventorymgmtApplication {

	public static void main(String[] args) { 
		SpringApplication.run(InventorymgmtApplication.class, args); 
		System.out.println("Application has started and SpringApplication.run completed.");
	}

}
