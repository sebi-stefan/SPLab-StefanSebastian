package org.example.splabstefansebastian.model;

import lombok.Data;

import java.util.List;

@Data
public class Book {

    private Author author;
    private TableOfContents tableOfContents;
    private List<Chapter> chapters;

    public Book(TableOfContents tableOfContents, List<Chapter> chapters) {
        this.tableOfContents = tableOfContents;
        this.chapters = chapters;
    }
}
