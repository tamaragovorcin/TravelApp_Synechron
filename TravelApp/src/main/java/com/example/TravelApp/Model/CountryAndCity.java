package com.example.TravelApp.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryAndCity implements Serializable {
    @Id
    private Long id;

    @Column(name = "country")
    @NotEmpty
    private String country;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "drzava")
    @NotEmpty
    private String drzava;

    @Column(name = "grad")
    @NotEmpty
    private String grad;
}
