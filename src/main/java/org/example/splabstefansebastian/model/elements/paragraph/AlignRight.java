package org.example.splabstefansebastian.model.elements.paragraph;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlignRight implements AlignStrategy{
    @Override
    public String render(String string) {
        return String.format("%80s%n", string);
    }
}
