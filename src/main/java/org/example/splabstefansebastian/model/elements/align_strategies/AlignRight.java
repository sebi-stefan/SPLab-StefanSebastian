package org.example.splabstefansebastian.model.elements.align_strategies;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlignRight implements AlignStrategy{
    @Override
    public String render(String string) {
        return String.format("%80s%n", string);
    }
}
