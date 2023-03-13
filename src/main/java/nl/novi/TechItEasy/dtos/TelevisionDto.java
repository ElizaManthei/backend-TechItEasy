package nl.novi.TechItEasy.dtos;

import nl.novi.TechItEasy.Models.Television;

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
    public TelevisionDto fromTelevision(Television television){
        TelevisionDto dto = new TelevisionDto();
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
        return dto;
    }
}
