package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "triptypes")
public class Triptype {
    @Id
    @Size(max = 1)
    @Column(name = "TripTypeId", nullable = false, length = 1)
    private String id;

    @Size(max = 25)
    @Column(name = "TTName", length = 25)
    private String tTName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTTName() {
        return tTName;
    }

    public void setTTName(String tTName) {
        this.tTName = tTName;
    }

}