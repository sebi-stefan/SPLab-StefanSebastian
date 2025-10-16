package org.example.splabstefansebastian.model.elements.paragraph;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlignLeft implements AlignStrategy{
    @Override
    public String render(String string) {
        return String.format("%-80s%n", string);
    }
}
