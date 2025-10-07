package org.example.splabstefansebastian;

import org.example.splabstefansebastian.model.*;
import org.example.splabstefansebastian.model.elements.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpLabStefanSebastianApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpLabStefanSebastianApplication.class, args);
		run();
	}

	private static void run(){
		Book book = new Book();
		book.setTitle("Noapte buna, copii!");
		book.setAuthors(new ArrayList<>(List.of(new Author("Radu Pavel Gheo"))));

		Section cap1 = new Section("Capitolul 1");
		Section cap11 = new Section("Capitolul 1.1");
		Section cap111 = new Section("Capitolul 1.1.1");
		Section cap1111 = new Section("Capitolul 1.1.1.1");
		book.addContent(new Paragraph("Multumesc celor care ..."));
		book.addContent(cap1);
		cap1.add(new Paragraph("Moto capitol"));
		cap1.add(cap11);
		cap11.add(new Paragraph("Text from subchapter 1.1"));
		cap11.add(cap111);
		cap111.add(new Paragraph("text from subchapter 1.1.1"));
		cap111.add(cap1111);
		cap1111.add(new Image("Image subchapter 1.1.1.1"));

		System.out.println(book);
	}

}
