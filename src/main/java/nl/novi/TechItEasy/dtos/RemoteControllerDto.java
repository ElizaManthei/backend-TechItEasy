package nl.novi.TechItEasy.dtos;

import jakarta.validation.constraints.NotBlank;
import nl.novi.TechItEasy.Models.RemoteController;
import java.util.ArrayList;
import java.util.List;

public class RemoteControllerDto {

    public Long id;
    public String compatibleWith;
    public String batteryType;
    @NotBlank
    public String name;
    public String brand;
    public Double price;
    public Integer originalStock;
    public String television;
    public RemoteControllerDto fromRemoteController(RemoteController remoteController) {
        RemoteControllerDto remoteControllerDto = new RemoteControllerDto();
        remoteControllerDto.id = remoteController.getId();
        remoteControllerDto.compatibleWith = remoteController.getCompatibleWith();
        remoteControllerDto.batteryType = remoteController.getBatteryType();
        remoteControllerDto.name = remoteController.getName();
        remoteControllerDto.brand = remoteController.getBrand();
        remoteControllerDto.price = remoteController.getPrice();
        remoteControllerDto.originalStock = remoteController.getOriginalStock();
        if (remoteController.getTelevision() != null) {
            remoteControllerDto.television = remoteController.getTelevision().getName();
        }
        return remoteControllerDto;
    }

    public List<RemoteControllerDto> fromRemoteControllerList(List<RemoteController> remoteControllerList) {
        List<RemoteControllerDto> remoteControllerDtoList = new ArrayList<>();
        for (RemoteController remoteController : remoteControllerList){
            remoteControllerDtoList.add(this.fromRemoteController(remoteController));
        }
        return remoteControllerDtoList;
    }
}
