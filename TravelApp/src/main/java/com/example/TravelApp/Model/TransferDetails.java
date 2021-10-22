package com.example.TravelApp.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferDetails implements Serializable {

    @Id
    @SequenceGenerator(name = "transfer_details_gen", sequenceName = "transfer_details_seq", initialValue = 10, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "travel_details_gen")
    private long id;

    @Column(name = "travelReqID")
    private String travelReqID;

    @Column(name = "flightId")
    private String flightId;

    @Column(name = "reservationDate")
    private LocalDate reservationDate;

    @Column(name = "reservationTime")
    private LocalTime reservationTime;

    @Column(name = "additionalComments")
    private String additionalComments;

    @Column(name = "departingCountry")
    private String departingCountry;

    @Column(name = "departFrom")
    private String departFrom;

    @Column(name = "arrivalCountry")
    private String arrivalCountry;

    @Column(name = "arriveTo")
    private String arriveTo;

    @Column(name = "departureDate")
    private LocalDate departureDate;

    @Column(name = "departureTime")
    private LocalTime departureTime;

    @Column(name = "arrivalDate")
    private LocalDate arrivalDate;

    @Column(name = "arrivalTime")
    private LocalTime arrivalTime;

    @Column(name = "previousId")
    private String previousId;

    @Column(name = "updateDate")
    private LocalDate updateDate;

    @Column(name = "updateTime")
    private LocalTime updateTime;

    @Column(name = "transferDate")
    private LocalDate transferDate;

    @Column(name = "transferTime")
    private LocalTime transferTime;

    @Column(name = "transferCity")
    private String transferCity;

    @Column(name = "transferAddress")
    private String transferAddress;

    @JsonBackReference(value="employee-transfer")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee", referencedColumnName = "id")
    private Employee employee;
}