package org.example.splabstefansebastian.model.elements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Paragraph extends AbstractElement{

    private String text;

}
