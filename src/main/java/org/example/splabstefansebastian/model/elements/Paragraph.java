package org.example.splabstefansebastian.model.elements;

import jakarta.persistence.Entity;
import lombok.*;
import org.example.splabstefansebastian.model.elements.align_strategies.AlignStrategy;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Paragraph extends BaseElement {

    private String text;

//    private AlignStrategy alignStrategy;

    public Paragraph(String text) {
        this.text = text;
    }

//    @Override
//    public String toString() {
//        if(alignStrategy == null) return text;
//        String renderedText = alignStrategy.render(text);
//
//        return "\n" + renderedText + "\n";
//    }
}
