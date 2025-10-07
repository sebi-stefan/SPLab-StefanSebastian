package org.example.splabstefansebastian.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.splabstefansebastian.model.elements.Element;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Book {

    private String title;
    private List<Author> authors;
    private List<Element> elements;

    public Book(String title, List<Author> authors,  List<Element> elements) {
        this.title = title;
        this.authors = authors;
        this.elements = new ArrayList<>(elements);
    }

    public void addContent(Element element){
        if(elements == null){
            elements = new ArrayList<>();
        }
        elements.add(element);
    }
}
