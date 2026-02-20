package com.app.dossier;

import com.app.dossier.configuration.DatabaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Import({DatabaseConfig.class})
@EnableJpaRepositories
@EnableWebMvc
@EntityScan("com.gslab.dzee.model.entity")
public class DossierApplication {

	public static void main(String[] args) {
		SpringApplication.run(DossierApplication.class, args);
	}

}
