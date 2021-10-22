package com.example.TravelApp.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDetails implements Serializable {
    @Id
    @SequenceGenerator(name = "id", sequenceName = "accommodation_details_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accommodation_details_gen")
    private long id;

    @Column(name = "accountName")
    private String accountName;

    @JsonBackReference(value="employee-accommodation")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee", referencedColumnName = "id", nullable = true)
    private Employee employee;

    @Column(name = "checkOutDate")
    private LocalDateTime checkOutDate;

    @Column(name = "checkInDate")
    private LocalDateTime checkInDate;

    @Column(name = "accommodationRequestNo")
    private String accommodationRequestNo;

    @Column(name = "bookingPurpose")
    private String bookingPurpose;

    @Column(name = "billingDetails")
    private String billingDetails;

    @Column(name = "previousId")
    private String previousId;

    @Column(name = "updateDate")
    private String updateDate;

    @Column(name = "updateTime")
    private String updateTime;

    @JsonManagedReference("travel-accommodation")
    @OneToMany(mappedBy = "accommodationDetails",  cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<TravelDetails> travelDetails = new HashSet<TravelDetails>();

    @Override
    public String toString() {
        return "AccommodationDetails{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", employee=" + employee +
                ", checkOutDate=" + checkOutDate +
                ", checkInDate=" + checkInDate +
                ", accommodationRequestNo='" + accommodationRequestNo + '\'' +
                ", bookingPurpose='" + bookingPurpose + '\'' +
                ", billingDetails='" + billingDetails + '\'' +
                ", previousId='" + previousId + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", travelDetails=" + travelDetails +
                '}';
    }
}
