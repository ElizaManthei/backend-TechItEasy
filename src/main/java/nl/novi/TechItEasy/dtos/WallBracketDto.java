package nl.novi.TechItEasy.dtos;

import nl.novi.TechItEasy.Models.Television;
import nl.novi.TechItEasy.Models.WallBracket;
import java.util.ArrayList;
import java.util.List;

public class WallBracketDto {
    public Long id;
    public String size;
    public Boolean ajustable;
    public String name;
    public Double price;
    public List<String> television = new ArrayList<>();

    public WallBracketDto fromWallBracket(WallBracket wallBracket) {
        WallBracketDto wallBracketDto = new WallBracketDto();
        wallBracketDto.id = wallBracket.getId();
        wallBracketDto.size = wallBracket.getSize();
        wallBracketDto.ajustable = wallBracket.getAjustable();
        wallBracketDto.name = wallBracket.getName();
        wallBracketDto.price = wallBracket.getPrice();
        if (wallBracket.getTelevisions() != null){
            for (Television t: wallBracket.getTelevisions()) {
                wallBracketDto.television.add(t.getName());
            }
        }
        return wallBracketDto;
    }

    public List<WallBracketDto> fromWallBracketList(List<WallBracket> wallBracketList) {
        List<WallBracketDto> wallBracketDtoList = new ArrayList<>();
        for (WallBracket w: wallBracketList) {
           wallBracketDtoList.add(this.fromWallBracket(w));
        }
        return wallBracketDtoList;
    }
}
