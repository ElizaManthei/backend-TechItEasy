package nl.novi.TechItEasy.dtos;

import nl.novi.TechItEasy.Models.CIModule;
import nl.novi.TechItEasy.Models.Television;
import java.util.ArrayList;
import java.util.List;

public class CIModuleDto {
    public Long id;
    public String name;
    public String type;
    public Double price;
    public List<String> televisions = new ArrayList<>();
public CIModuleDto fromCIModule(CIModule ciModule) {
    CIModuleDto ciModuleDto = new CIModuleDto();
    ciModuleDto.id = ciModule.getId();
    ciModuleDto.name = ciModule.getName();
    ciModuleDto.type = ciModule.getType();
    ciModuleDto.price = ciModule.getPrice();
    if (ciModule.getTelevisions() != null) {
        for (Television t: ciModule.getTelevisions()) {
            ciModuleDto.televisions.add(t.getName());
        }
    }
        return ciModuleDto;
   }

    public List<CIModuleDto> fromCIModuleList(List<CIModule> ciModuleList) {
        List<CIModuleDto> ciModuleDtoList = new ArrayList<>();
        for (CIModule t : ciModuleList) {
            ciModuleDtoList.add(this.fromCIModule(t));
        }
        return ciModuleDtoList;
    }
}
