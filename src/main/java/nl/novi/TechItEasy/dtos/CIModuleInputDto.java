package nl.novi.TechItEasy.dtos;

import jakarta.validation.constraints.NotBlank;
import nl.novi.TechItEasy.Models.CIModule;

public class CIModuleInputDto {
    public Long id;
    @NotBlank
    public String name;
    public String type;
    public Double price;

    public CIModule toCIModule(CIModule ciModule) {
    if(this.id != null) {
        ciModule.setId(this.id);
    }
    if (this.name != null) {
        ciModule.setName(this.name);
    }
    if (this.type != null) {
        ciModule.setType(this.type);
    }
    if (this.price != null) {
        ciModule.setPrice(this.price);
    }
        return ciModule;
    }
}
