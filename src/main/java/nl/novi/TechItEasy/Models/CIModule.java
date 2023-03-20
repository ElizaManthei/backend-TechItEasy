package nl.novi.TechItEasy.Models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ci_module")
public class CIModule {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private Double price;
    @OneToMany(mappedBy = "ciModule")
    private List<Television> televisions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Television> getTelevisions() {
        return televisions;
    }

    public void setTelevisions(List<Television> television) {
        this.televisions = television;
    }
}
