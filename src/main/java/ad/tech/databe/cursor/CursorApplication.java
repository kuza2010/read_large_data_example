package ad.tech.databe.cursor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableJpaRepositories
public class CursorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursorApplication.class, args);
	}

}
