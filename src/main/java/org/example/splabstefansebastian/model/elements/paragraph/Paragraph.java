package org.example.splabstefansebastian.model.elements.paragraph;

import lombok.*;
import org.example.splabstefansebastian.model.elements.AbstractElement;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class Paragraph extends AbstractElement {

    private String text;

    private AlignStrategy alignStrategy;

    public Paragraph(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        if(alignStrategy == null) return text;
        String renderedText = alignStrategy.render(text);

        return "\n" + renderedText + "\n";
    }
}
