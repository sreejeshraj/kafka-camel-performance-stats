package com.sreejesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@ImportResource("classpath:META-INF/spring/applicationContext.xml") 
public class KafkaMainClass {

	public static void main(String[] args) {
		SpringApplication.run(KafkaMainClass.class, args);

	}

}
