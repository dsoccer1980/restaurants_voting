package ru.dsoccer1980.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;


@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(max = 100)
    private String address;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getAddress());
    }

    public Restaurant(Integer id, String name, String address) {
        super(id, name);
        Objects.requireNonNull(address, "address cannot be null");
        this.address = address;
    }

    public Restaurant(String name, @NotBlank @Size(max = 200) String address) {
        this(null, name, address);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + id +
                ", name=" + name +
                ", address=" + address +
                '}';
    }
}