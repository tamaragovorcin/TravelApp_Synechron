package com.example.TravelApp.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DecisionTripInfo {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "arrivalCountry")
    @NotEmpty
    private String arrivalCountry;

    @Column(name = "arrivalCity")
    @NotEmpty
    private String arrivalCity;

    @Column(name = "departureDate")
    @NotEmpty
    private String departureDate;

    @JsonBackReference(value="decision-trip-info")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "decisionForABusinessTrip", referencedColumnName = "id")
    private DecisionForABusinessTrip decisionForABusinessTrip;
}
