package org.example.splabstefansebastian.model.elements;

public abstract class AbstractElement implements Element{
    @Override
    public void add(Element element) {
        throw new UnsupportedOperationException("Unsupported operation!");
    }

    @Override
    public void remove(Element element) {
        throw new UnsupportedOperationException("Unsupported operation!");
    }

    @Override
    public Element get(int index) {
        throw new UnsupportedOperationException("Unsupported operation!");
    }
}
