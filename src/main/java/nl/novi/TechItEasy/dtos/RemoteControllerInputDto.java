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
        if (this.id != null) {
        remoteController.setId(this.id);
        }
        if (this.compatibleWith != null) {
            remoteController.setCompatibleWith(this.compatibleWith);
        }
        if (this.batteryType != null) {
            remoteController.setBatteryType(this.batteryType);
        }
        if (this.name != null) {
            remoteController.setName(this.name);
        }
        if (this.brand != null) {
            remoteController.setBrand(this.brand);
        }
        if (this.price != null) {
            remoteController.setPrice(this.price);
        }
        if (this.originalStock != null) {
            remoteController.setOriginalStock(this.originalStock);
        }
        return remoteController;
    }
}
