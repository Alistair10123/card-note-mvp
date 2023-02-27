package com.card.note.mvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.card.note.mvp"})
public class CardNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardNoteApplication.class, args);
	}

}
