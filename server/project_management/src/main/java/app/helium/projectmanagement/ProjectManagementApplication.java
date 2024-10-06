package app.helium.projectmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ProjectManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}
}
