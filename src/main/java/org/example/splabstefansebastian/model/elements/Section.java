package org.example.splabstefansebastian.model.elements;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Section extends AbstractElement {

    private String title;
    private List<Element> elements;

    public Section(String title, List<Element> elements){
        this.title = title;
        this.elements = new ArrayList<>(elements);
    }

    public Section(String title){
        this.title = title;
        this.elements = new ArrayList<>();
    }

    @Override
    public void add(Element element) {
        this.elements.add(element);
    }

    @Override
    public void remove(Element element) {
        this.elements.remove(element);
    }

    public void remove(int index){
        this.remove(this.get(index));
    }

    @Override
    public Element get(int index) {
        return this.elements.get(index);
    }
}
