package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.sql.Date;
import java.time.Instant;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingId", nullable = false)
    private Integer id;

    @Column(name = "BookingDate")
    private Date bookingDate;

    @Size(max = 50)
    @Column(name = "BookingNo", length = 50)
    private String bookingNo;

    @Column(name = "TravelerCount")
    private Double travelerCount;

    //@ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "CustomerId")
    @Column(name = "CustomerId")
    private Integer customer;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "TripTypeId")
    @Column(name = "TripTypeId")
    private String tripType;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "PackageId")
    @Column(name = "PackageId")
    private Integer packageField;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    public Double getTravelerCount() {
        return travelerCount;
    }

    public void setTravelerCount(Double travelerCount) {
        this.travelerCount = travelerCount;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public Integer getPackageField() {
        return packageField;
    }

    public void setPackageField(Integer packageField) {
        this.packageField = packageField;
    }
}