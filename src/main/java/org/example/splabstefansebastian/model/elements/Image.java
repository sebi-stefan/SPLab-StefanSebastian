package org.example.splabstefansebastian.model.elements;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image extends BaseElement{
    private String imageName;
    private String url;

    public Image(String imageName) {
        this.imageName = imageName;
    }
}
