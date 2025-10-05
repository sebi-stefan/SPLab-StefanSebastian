package org.example.splabstefansebastian.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Chapter {

    private String name;
    private List<SubChapter> subChapters;

    public Chapter(String name, List<SubChapter> subChapters) {
        this.name = name;
        this.subChapters = new ArrayList<>(subChapters);
    }
}
