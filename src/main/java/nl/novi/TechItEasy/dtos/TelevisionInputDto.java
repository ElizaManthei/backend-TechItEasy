package nl.novi.TechItEasy.dtos;

import jakarta.validation.constraints.NotBlank;
import nl.novi.TechItEasy.Models.Television;

public class TelevisionInputDto {

    public Long id;
    public String type;
    public String brand;
    @NotBlank
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
    public Television toTelevision(){
        Television television = new Television();
        television.setType(this.type);
        television.setBrand(this.brand);
        television.setName(this.name);
        television.setPrice(this.price);
        television.setAvailableSize(this.availableSize);
        television.setRefreshRate(this.refreshRate);
        television.setScreenType(this.screenType);
        television.setScreenQuality(this.screenQuality);
        television.setSmartTv(this.smartTv);
        television.setWifi(this.wifi);
        television.setVoiceControl(this.voiceControl);
        television.setHdr(this.hdr);
        television.setBluetooth(this.bluetooth);
        television.setAmbiLight(this.ambiLight);
        television.setOriginalStock(this.originalStock);
        television.setSold(this.sold);
        return television;
    }
}
