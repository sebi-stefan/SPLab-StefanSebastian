package org.example.splabstefansebastian.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.splabstefansebastian.model.elements.BaseElement;
import org.example.splabstefansebastian.model.elements.Element;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Book {

    @Id
    private Long id;

    private String title;

    @ManyToMany
    private List<Author> authors;

    @OneToMany(targetEntity = BaseElement.class)
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
