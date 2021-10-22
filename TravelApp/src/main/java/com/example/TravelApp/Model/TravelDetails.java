package com.example.TravelApp.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelDetails implements Serializable {

    @Id
    @SequenceGenerator(name = "travel_details_gen", sequenceName = "travel_details_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "travel_details_gen")
    private long id;

    @Column(name = "departFrom")
    private String departFrom;

    @Column(name = "arriveTo")
    private String arriveTo;

    @Column(name = "departureDate")
    private LocalDate departureDate;

    @Column(name = "travelReqID")
    private String travelReqID;

    @Column(name = "accountName")
    private String accountName;

    @Column(name = "approvedBy")
    private String approvedBy;

    @JsonBackReference(value="employee-travel")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee", referencedColumnName = "id")
    private Employee employee;

    @Column(name = "visaDetail")
    private String visaDetail;

    @Column(name = "tripPaidBy")
    private String tripPaidBy;

    @Column(name = "arrivalTime")
    private LocalTime arrivalTime;

    @Column(name = "arrivalDate")
    private LocalDate arrivalDate;

    @Column(name = "departureTime")
    private LocalTime departureTime;

    @Column(name = "departingCountry")
    private String departingCountry;

    @Column(name = "arrivalCountry")
    private String arrivalCountry;

    @Column(name = "additionalComments")
    private String additionalComments;

    @Column(name = "bookingPurpose")
    private String bookingPurpose;

    @Column(name = "partOfTheDayFrom")
    private LocalTime partOfTheDayFrom;

    @Column(name = "partOfTheDayTo")
    private LocalTime partOfTheDayTo;

    @Column(name = "returnTrip")
    private String returnTrip;

    @Column(name = "previousId")
    private long previousId;

    @Column(name = "updateDate")
    private String updateDate;

    @Column(name = "updateTime")
    private String updateTime;


    @Column(name = "mailSent")
    private Boolean mailSent;

    @JsonBackReference(value="travel-accommodation")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "accommodationDetails", referencedColumnName = "id")
    private AccommodationDetails accommodationDetails;


    @Override
    public String toString() {
        return "TravelDetails{" +
                "id=" + id +
                ", departFrom='" + departFrom + '\'' +
                ", arriveTo='" + arriveTo + '\'' +
                ", departureDate=" + departureDate +
                ", travelReqID='" + travelReqID + '\'' +
                ", accountName='" + accountName + '\'' +
                ", approvedBy='" + approvedBy + '\'' +
                ", employee=" + employee +
                ", visaDetail='" + visaDetail + '\'' +
                ", tripPaidBy='" + tripPaidBy + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", arrivalDate=" + arrivalDate +
                ", departureTime=" + departureTime +
                ", departingCountry='" + departingCountry + '\'' +
                ", arrivalCountry='" + arrivalCountry + '\'' +
                ", additionalComments='" + additionalComments + '\'' +
                ", bookingPurpose='" + bookingPurpose + '\'' +
                ", partOfTheDayFrom=" + partOfTheDayFrom +
                ", partOfTheDayTo=" + partOfTheDayTo +
                ", returnTrip='" + returnTrip + '\'' +
                ", previousId=" + previousId +
                ", updateDate='" + updateDate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", mailSent=" + mailSent +
                ", accommodationDetails=" + accommodationDetails.getId() +
                '}';
    }
}
