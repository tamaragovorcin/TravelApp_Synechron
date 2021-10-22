package com.example.TravelApp.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Position implements Serializable {
    @Id
    private Long id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @JsonManagedReference("employee-position")
    @OneToMany(mappedBy = "position", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<Employee>();

}
