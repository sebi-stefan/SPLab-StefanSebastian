package org.example.splabstefansebastian;

import org.example.splabstefansebastian.model.*;
import org.example.splabstefansebastian.model.elements.*;
import org.example.splabstefansebastian.model.elements.paragraph.AlignCenter;
import org.example.splabstefansebastian.model.elements.paragraph.AlignLeft;
import org.example.splabstefansebastian.model.elements.paragraph.AlignRight;
import org.example.splabstefansebastian.model.elements.paragraph.Paragraph;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpLabStefanSebastianApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpLabStefanSebastianApplication.class, args);
		run();
	}

	private static void run(){
		Section cap1 = new Section("Capitolul 1");
		Paragraph p1 = new Paragraph("Paragraph 1");
		cap1.add(p1);
		Paragraph p2 = new Paragraph("Paragraph 2");
		cap1.add(p2);
		Paragraph p3 = new Paragraph("Paragraph 3");
		cap1.add(p3);
		Paragraph p4 = new Paragraph("Paragraph 4");
		cap1.add(p4);

		System.out.println("Printing without Alignment\n");
		System.out.println(cap1);

		p1.setAlignStrategy(new AlignCenter());
		p2.setAlignStrategy(new AlignRight());
		p3.setAlignStrategy(new AlignLeft());

		System.out.println();
		System.out.println("Printing with Alignment\n");

		System.out.println(cap1);


	}

}
