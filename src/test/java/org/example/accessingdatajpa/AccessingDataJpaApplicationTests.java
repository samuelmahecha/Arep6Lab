package org.example.accessingdatajpa;

import org.example.AccessingDataJpaApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AccessingDataJpaApplication.class)
class AccessingDataJpaApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		assertNotNull(context, "The application context should have loaded.");
	}

	@Test
	void commandLineRunnerBeanExists() {
		CommandLineRunner runner = context.getBean(CommandLineRunner.class);
		assertNotNull(runner, "The CommandLineRunner bean should have been created.");
	}

	@Test
	void commandLineRunnerLogsMessage() {
		CommandLineRunner runner = context.getBean(CommandLineRunner.class);
		assertDoesNotThrow(() -> runner.run(), "The CommandLineRunner should run without throwing exceptions.");
	}

	@Test
	void applicationRunsWithoutExceptions() {
		assertDoesNotThrow(() -> SpringApplication.run(AccessingDataJpaApplication.class), "The application should run without throwing exceptions.");
	}


}