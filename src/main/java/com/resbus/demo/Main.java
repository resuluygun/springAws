package com.resbus.demo;

import com.resbus.demo.config.PropertyFileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		PropertyFileReader t = new PropertyFileReader();
		try {
			t.getPropValues();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		SpringApplication.run(Main.class, args);
	}

}
