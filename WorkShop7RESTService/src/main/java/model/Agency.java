package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "agencies")
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AgencyId", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "AgncyAddress", length = 50)
    private String agncyAddress;

    @Size(max = 50)
    @Column(name = "AgncyCity", length = 50)
    private String agncyCity;

    @Size(max = 50)
    @Column(name = "AgncyProv", length = 50)
    private String agncyProv;

    @Size(max = 50)
    @Column(name = "AgncyPostal", length = 50)
    private String agncyPostal;

    @Size(max = 50)
    @Column(name = "AgncyCountry", length = 50)
    private String agncyCountry;

    @Size(max = 50)
    @Column(name = "AgncyPhone", length = 50)
    private String agncyPhone;

    @Size(max = 50)
    @Column(name = "AgncyFax", length = 50)
    private String agncyFax;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgncyAddress() {
        return agncyAddress;
    }

    public void setAgncyAddress(String agncyAddress) {
        this.agncyAddress = agncyAddress;
    }

    public String getAgncyCity() {
        return agncyCity;
    }

    public void setAgncyCity(String agncyCity) {
        this.agncyCity = agncyCity;
    }

    public String getAgncyProv() {
        return agncyProv;
    }

    public void setAgncyProv(String agncyProv) {
        this.agncyProv = agncyProv;
    }

    public String getAgncyPostal() {
        return agncyPostal;
    }

    public void setAgncyPostal(String agncyPostal) {
        this.agncyPostal = agncyPostal;
    }

    public String getAgncyCountry() {
        return agncyCountry;
    }

    public void setAgncyCountry(String agncyCountry) {
        this.agncyCountry = agncyCountry;
    }

    public String getAgncyPhone() {
        return agncyPhone;
    }

    public void setAgncyPhone(String agncyPhone) {
        this.agncyPhone = agncyPhone;
    }

    public String getAgncyFax() {
        return agncyFax;
    }

    public void setAgncyFax(String agncyFax) {
        this.agncyFax = agncyFax;
    }

}