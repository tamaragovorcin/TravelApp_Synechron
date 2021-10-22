package com.example.TravelApp.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DecisionForABusinessTrip{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "decisionNumber")
    @NotEmpty
    private String decisionNumber;

    @Column(name = "decisionDate")
    private LocalDate decisionDate;

    @Column(name = "authorizedPerson")
    private String authorizedPerson;

    @Column(name = "theBasisOfAuthorization")
    private String theBasisOfAuthorization;

    @Column(name = "employeeFullName")
    private String employeeFullName;

    @Column(name = "employeeDesignationName")
    private String employeeDesignationName;

    @Column(name = "travelPurpose")
    private String travelPurpose;

    @Column(name = "travelDuration")
    private String travelDuration;

    @Column(name = "waysOfTransport")
    private String waysOfTransport;

    @Column(name = "freeAccommodationAndFoodOnATrip")
    private String freeAccommodationAndFoodOnATrip;

    @Column(name = "advancePaymentEUR")
    private Double advancePaymentEUR;

    @Column(name = "advancePaymentRSD")
    private Double advancePaymentRSD;

    @Column(name = "dailyAllowanceEUR")
    private Double dailyAllowanceEUR;

    @Column(name = "dailyAllowanceRSD")
    private Double dailyAllowanceRSD;

    @Column(name = "travelReqID")
    private String travelReqID;

    @JsonManagedReference("decision-trip-info")
    @OneToMany(mappedBy = "decisionForABusinessTrip",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DecisionTripInfo> decisionTripInfo = new HashSet<DecisionTripInfo>();


}
