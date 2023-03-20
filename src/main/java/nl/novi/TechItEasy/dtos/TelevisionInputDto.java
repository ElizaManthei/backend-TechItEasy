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

public Television toTelevision(Television television) {

    if (this.id != null) {
        television.setId(this.id);
    }
        if(this.brand != null) {
            television.setBrand(this.brand);
        }
        if (this.type != null) {
            television.setType(this.type);
        }
        if (this.name != null) {
            television.setName(this.name);
        }
        if (this.price != null) {
            television.setPrice(this.price);
        }
        if (this.availableSize != null) {
            television.setAvailableSize(this.availableSize);
        }
        if (this.refreshRate != null) {
            television.setRefreshRate(this.refreshRate);
        }
        if (this.screenType != null) {
            television.setScreenType(this.screenType);
        }
        if (this.screenQuality != null) {
            television.setScreenQuality(this.screenQuality);
        }
        if (this.smartTv != null) {
            television.setSmartTv(this.smartTv);
        }
        if (this.wifi != null) {
            television.setWifi(this.wifi);
        }
        if (this.voiceControl != null) {
            television.setVoiceControl(this.voiceControl);
        }
        if (this.hdr != null) {
            television.setHdr(this.hdr);
        }
        if (this.bluetooth != null) {
            television.setBluetooth(this.bluetooth);
        }
        if (this.ambiLight != null) {
            television.setAmbiLight(this.ambiLight);
        }
        if (this.originalStock != null) {
            television.setOriginalStock(this.originalStock);
        }
        if (this.sold != null) {
            television.setSold(this.sold);
        }
        return television;
    }
}
