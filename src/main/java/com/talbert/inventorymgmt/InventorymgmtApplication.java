package com.talbert.inventorymgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.annotation.PreDestroy;

@SpringBootApplication
public class InventorymgmtApplication {

	public static void main(String[] args) { 
		try {
			SpringApplication.run(InventorymgmtApplication.class, args); 
			System.out.println("Application has started and SpringApplication.run completed.");
		} catch( Exception e) {
			System.out.println("Exception caught. Application crashed:");
			e.printStackTrace(System.out);
		}
	}

	@PreDestroy
    public void onExit() {
        System.out.println("ShutdownLogger: Application is shutting down.");
    }
}
