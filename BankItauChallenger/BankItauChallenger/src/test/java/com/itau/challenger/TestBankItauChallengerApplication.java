package com.itau.challenger;

import org.springframework.boot.SpringApplication;

public class TestBankItauChallengerApplication {

	public static void main(String[] args) {
		SpringApplication.from(BankItauChallengerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
