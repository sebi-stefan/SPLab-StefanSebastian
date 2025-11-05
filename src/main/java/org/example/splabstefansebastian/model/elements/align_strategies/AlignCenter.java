package org.example.splabstefansebastian.model.elements.align_strategies;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlignCenter implements AlignStrategy{
    @Override
    public String render(String string) {
        if (string.length() >= 80) return string;
        int leftPad = (80 - string.length()) / 2;
        int rightPad = 80 - string.length() - leftPad;
        return " ".repeat(leftPad) + string + " ".repeat(rightPad);
    }
}
