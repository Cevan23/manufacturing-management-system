package com.manufacturing.manufacturingmanagementsystem;

import com.manufacturing.manufacturingmanagementsystem.model.audit.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ManufacturingManagementSystemApplication {

	/**
	 * This method is used to get the current auditor of the application.
	 *
	 * @return AuditorAware<String>
	 */
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(ManufacturingManagementSystemApplication.class, args);
	}

}
