package org.example.splabstefansebastian;

import org.example.splabstefansebastian.model.*;
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
		Table table = new Table("table");
		Image image = new Image("image");
		Paragraph paragraph = new Paragraph("text");
		List<SubChapter> subChapters = new ArrayList<>(List.of(
				new SubChapter("subChapter1", List.of(table), List.of(image), List.of(paragraph)),
				new SubChapter("subChapter2", List.of(table), List.of(image), List.of(paragraph)),
				new SubChapter("subChapter3", List.of(table), List.of(image), List.of(paragraph))
		));

		List<Chapter> chapters = new ArrayList<>(List.of(
				new Chapter("chapter1", new ArrayList<>(subChapters)),
				new Chapter("chapter2", new ArrayList<>(subChapters)),
				new Chapter("chapter3", new ArrayList<>(subChapters))
		));
		Book book = new Book(new TableOfContents(), chapters);
		book.setAuthor(new Author("Vasile Alecsandri"));
		System.out.println(book);
	}

}
