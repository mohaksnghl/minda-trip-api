package demo.minda.tripapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "demo.minda.tripapi")
public class TripApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripApiApplication.class, args);
	}

}
