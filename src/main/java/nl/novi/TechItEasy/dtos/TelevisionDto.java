package nl.novi.TechItEasy.dtos;

import nl.novi.TechItEasy.Models.Television;
import nl.novi.TechItEasy.Models.WallBracket;
import java.util.ArrayList;
import java.util.List;

public class TelevisionDto {
    public Long id;
    public String type;
    public String brand;
    public String name;
    public Double price;
    public Double  availableSize;
    public Double refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean wifi;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambiLight;
    public Integer originalStock;
    public Integer sold;
    public RemoteControllerDto remoteController = null;
    public CIModuleDto ciModule = null;
    public List<WallBracketDto> wallBrackets = new ArrayList<>();
    public TelevisionDto fromTelevision(Television television) {
        TelevisionDto dto = new TelevisionDto();
        RemoteControllerDto remoteControllerDto = new RemoteControllerDto();
        CIModuleDto ciModuleDto = new CIModuleDto();
        WallBracketDto wallBracketDto = new WallBracketDto();
        dto.id = television.getId();
        dto.type = television.getType();
        dto.brand = television.getBrand();
        dto.name = television.getName();
        dto.price = television.getPrice();
        dto.availableSize = television.getAvailableSize();
        dto.refreshRate = television.getRefreshRate();
        dto.screenType = television.getScreenType();
        dto.screenQuality = television.getScreenQuality();
        dto.smartTv = television.getSmartTv();
        dto.wifi = television.getWifi();
        dto.voiceControl = television.getVoiceControl();
        dto.hdr = television.getHdr();
        dto.bluetooth = television.getBluetooth();
        dto.ambiLight = television.getAmbiLight();
        dto.originalStock = television.getOriginalStock();
        dto.sold = television.getSold();
        if(television.getRemoteController() != null) {
            dto.remoteController = remoteControllerDto.fromRemoteController(television.getRemoteController());
        }
        if(television.getCiModule() != null) {
            dto.ciModule = ciModuleDto.fromCIModule(television.getCiModule());
        }
        if (television.getWallBrackets() != null) {
            for (WallBracket w : television.getWallBrackets()) {
                 dto.wallBrackets.add(wallBracketDto.fromWallBracket(w));
            }
        }
        return dto;
    }

    public List<TelevisionDto> fromTelevisionList(List<Television> televisionList) {
        List<TelevisionDto> televisionDtoList = new ArrayList<>();
        for (Television t : televisionList){
            televisionDtoList.add(this.fromTelevision(t));
        }
        return televisionDtoList;
    }

}
