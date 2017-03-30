package pl.edu.amu.bawsj.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

@Autowired
SimpleRestService simpleRestService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
