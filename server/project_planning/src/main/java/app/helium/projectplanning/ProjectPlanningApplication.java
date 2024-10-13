package app.helium.projectplanning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ProjectPlanningApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectPlanningApplication.class, args);
	}

}
