package co.edu.uco.quotes.api.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "co.edu.uco.quotes")
public class ApiQuotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiQuotesApplication.class, args);
	}

}
