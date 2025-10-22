package com.blingo.lingdyo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LingdyoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LingdyoApplication.class, args);
		ConexionMySQL conn = new ConexionMySQL();
		conn.tablasBase();
	}
}
