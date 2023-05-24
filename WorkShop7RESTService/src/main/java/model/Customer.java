package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerId", nullable = false)
    private Integer id;

    @Size(max = 25)
    @NotNull
    @Column(name = "CustFirstName", nullable = false, length = 25)
    private String custFirstName;

    @Size(max = 25)
    @NotNull
    @Column(name = "CustLastName", nullable = false, length = 25)
    private String custLastName;

    @Size(max = 75)
    @NotNull
    @Column(name = "CustAddress", length = 75)
    private String custAddress;

    @Size(max = 50)
    @NotNull
    @Column(name = "CustCity", nullable = false, length = 50)
    private String custCity;

    @Size(max = 2)
    @NotNull
    @Column(name = "CustProv", nullable = false, length = 2)
    private String custProv;

    @Size(max = 7)
    @NotNull
    @Column(name = "CustPostal", nullable = false, length = 7)
    private String custPostal;

    @Size(max = 25)
    @Column(name = "CustCountry", length = 25)
    private String custCountry;

    @Size(max = 20)
    @Column(name = "CustHomePhone", length = 20)
    private String custHomePhone;

    @Size(max = 20)
    @NotNull
    @Column(name = "CustBusPhone", nullable = false, length = 20)
    private String custBusPhone;

    @Size(max = 50)
    @NotNull
    @Column(name = "CustEmail", nullable = false, length = 50)
    private String custEmail;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "AgentId")
    @Column(name = "AgentId", nullable = false, length = 50)
    private Integer agent;

    @Size(max = 50)
    @Column(name = "Username", length = 50)
    private String username;

    @Size(max = 50)
    @Column(name = "Password", length = 50)
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustCity() {
        return custCity;
    }

    public void setCustCity(String custCity) {
        this.custCity = custCity;
    }

    public String getCustProv() {
        return custProv;
    }

    public void setCustProv(String custProv) {
        this.custProv = custProv;
    }

    public String getCustPostal() {
        return custPostal;
    }

    public void setCustPostal(String custPostal) {
        this.custPostal = custPostal;
    }

    public String getCustCountry() {
        return custCountry;
    }

    public void setCustCountry(String custCountry) {
        this.custCountry = custCountry;
    }

    public String getCustHomePhone() {
        return custHomePhone;
    }

    public void setCustHomePhone(String custHomePhone) {
        this.custHomePhone = custHomePhone;
    }

    public String getCustBusPhone() {
        return custBusPhone;
    }

    public void setCustBusPhone(String custBusPhone) {
        this.custBusPhone = custBusPhone;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public Integer getAgent() {
        return agent;
    }

    public void setAgent(Integer agent) {
        this.agent = agent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}