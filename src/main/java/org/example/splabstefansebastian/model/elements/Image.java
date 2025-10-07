package org.example.splabstefansebastian.model.elements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Image extends AbstractElement{
    private String imageName;
    private String url;

    public Image(String imageName) {
        this.imageName = imageName;
    }
}
