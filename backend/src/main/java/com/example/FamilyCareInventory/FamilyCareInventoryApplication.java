package com.example.FamilyCareInventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone;

@SpringBootApplication
public class FamilyCareInventoryApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone( "Asia/Shanghai"));
		SpringApplication.run(FamilyCareInventoryApplication.class, args);
	}
}
