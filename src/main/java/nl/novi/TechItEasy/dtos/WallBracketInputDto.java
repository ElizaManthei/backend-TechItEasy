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
    wallBracket.setSize(this.size);
    wallBracket.setAjustable(this.ajustable);
    wallBracket.setName(this.name);
    wallBracket.setPrice(this.price);
    return wallBracket;
    }
}
