package nl.novi.TechItEasy.dtos;

import jakarta.validation.constraints.NotBlank;
import nl.novi.TechItEasy.Models.WallBracket;

public class WallBracketInputDto {
    public Long id;
    public String size;
    public Boolean ajustable;
    @NotBlank
    public String name;
    public Double price;
public WallBracket toWallBracket(WallBracket wallBracket) {
    if (this.id != null) {
        wallBracket.setId(this.id);
    }
    if (this.size != null) {
        wallBracket.setSize(this.size);
    }
    if (this.ajustable != null) {
        wallBracket.setAjustable(this.ajustable);
    }
    if (this.name != null) {
        wallBracket.setName(this.name);
    }
    if (this.price != null) {
        wallBracket.setPrice(this.price);
    }
    return wallBracket;
    }
}
