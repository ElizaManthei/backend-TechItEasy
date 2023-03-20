package nl.novi.TechItEasy.dtos;

import jakarta.validation.constraints.NotBlank;
import nl.novi.TechItEasy.Models.RemoteController;

public class RemoteControllerInputDto {
    public Long id;
    public String compatibleWith;
    public String batteryType;
    @NotBlank
    public String name;
    public String brand;
    public Double price;
    public Integer originalStock;
    public RemoteController toRemoteController(RemoteController remoteController) {
        remoteController.setCompatibleWith(this.compatibleWith);
        remoteController.setBatteryType(this.batteryType);
        remoteController.setName(this.name);
        remoteController.setBrand(this.brand);
        remoteController.setPrice(this.price);
        remoteController.setOriginalStock(this.originalStock);
        return remoteController;
    }
}
