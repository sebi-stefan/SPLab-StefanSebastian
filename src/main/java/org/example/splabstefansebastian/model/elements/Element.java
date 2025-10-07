package org.example.splabstefansebastian.model.elements;

public interface Element {

    void add(Element element);

    void remove(Element element);

    Element get(int index);
}
