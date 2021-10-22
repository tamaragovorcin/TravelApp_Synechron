package com.example.TravelApp.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    @Id
    @SequenceGenerator(name = "employee_gen", sequenceName = "employee_seq", initialValue = 10, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_gen")
    private long id;

    @Column(name = "employeeId")
    private String employeeId;

    @Column(name = "email")
    private String email;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "designationName")
    private String designationName;

    @JsonManagedReference("employee-travel")
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TravelDetails> travelDetails = new HashSet<TravelDetails>();

    @JsonManagedReference("employee-accommodation")
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AccommodationDetails> accommodationDetails = new HashSet<AccommodationDetails>();

    @JsonManagedReference("employee-transfer")
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TransferDetails> transferDetails = new HashSet<TransferDetails>();

    @JsonBackReference(value="employee-position")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "position", referencedColumnName = "id")
    private Position position;
}
