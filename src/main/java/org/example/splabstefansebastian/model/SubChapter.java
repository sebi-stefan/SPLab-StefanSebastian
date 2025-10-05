package org.example.splabstefansebastian.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubChapter {

    private String name;
    private List<Table> tables;
    private List<Image> images;
    private List<Paragraph> paragraphs;

    public SubChapter(String name,
                      List<Table> tables,
                      List<Image> images,
                      List<Paragraph> paragraphs) {
        this.name = name;
        this.tables = new ArrayList<>(tables);
        this.images = new ArrayList<>(images);
        this.paragraphs = new ArrayList<>(paragraphs);
    }
}
