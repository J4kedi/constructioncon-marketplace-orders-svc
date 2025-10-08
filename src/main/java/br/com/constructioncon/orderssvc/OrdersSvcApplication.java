package br.com.constructioncon.orderssvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.constructioncon.orderssvc"})
public class OrdersSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersSvcApplication.class, args);
	}

}
