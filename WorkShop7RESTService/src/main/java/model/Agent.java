package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "agents")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AgentId", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "AgtFirstName", length = 20)
    private String agtFirstName;

    @Size(max = 5)
    @Column(name = "AgtMiddleInitial", length = 5)
    private String agtMiddleInitial;

    @Size(max = 20)
    @Column(name = "AgtLastName", length = 20)
    private String agtLastName;

    @Size(max = 20)
    @Column(name = "AgtBusPhone", length = 20)
    private String agtBusPhone;

    @Size(max = 50)
    @Column(name = "AgtEmail", length = 50)
    private String agtEmail;

    @Size(max = 20)
    @Column(name = "AgtPosition", length = 20)
    private String agtPosition;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "AgencyId")
    @Column(name = "AgencyId", length = 50)
    private Integer agency;

    @Size(max = 10)
    @Column(name = "AgtUserId", length = 10)
    private String agtUserId;

    @Size(max = 15)
    @Column(name = "AgtPassword", length = 15)
    private String agtPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgtFirstName() {
        return agtFirstName;
    }

    public void setAgtFirstName(String agtFirstName) {
        this.agtFirstName = agtFirstName;
    }

    public String getAgtMiddleInitial() {
        return agtMiddleInitial;
    }

    public void setAgtMiddleInitial(String agtMiddleInitial) {
        this.agtMiddleInitial = agtMiddleInitial;
    }

    public String getAgtLastName() {
        return agtLastName;
    }

    public void setAgtLastName(String agtLastName) {
        this.agtLastName = agtLastName;
    }

    public String getAgtBusPhone() {
        return agtBusPhone;
    }

    public void setAgtBusPhone(String agtBusPhone) {
        this.agtBusPhone = agtBusPhone;
    }

    public String getAgtEmail() {
        return agtEmail;
    }

    public void setAgtEmail(String agtEmail) {
        this.agtEmail = agtEmail;
    }

    public String getAgtPosition() {
        return agtPosition;
    }

    public void setAgtPosition(String agtPosition) {
        this.agtPosition = agtPosition;
    }

    public Integer getAgency() {
        return agency;
    }

    public void setAgency(Integer agency) {
        this.agency = agency;
    }

    public String getAgtUserId() {
        return agtUserId;
    }

    public void setAgtUserId(String agtUserId) {
        this.agtUserId = agtUserId;
    }

    public String getAgtPassword() {
        return agtPassword;
    }

    public void setAgtPassword(String agtPassword) {
        this.agtPassword = agtPassword;
    }


}