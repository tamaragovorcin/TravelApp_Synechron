package com.example.TravelApp.DTOs;

import com.example.TravelApp.Model.TravelDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDetailsDTO {
    private long id;
    private String accountName;
    private String employeeId;
    private String accommodationRequestNo;
    private String bookingPurpose;
    private String billingDetails;
    private Set<TravelDetailsDTO> travelDetails = new HashSet<TravelDetailsDTO>();
    private String previousId;
    private String updateDate;
    private String updateTime;
    private String checkOutDate;
    private String checkInDate;


    @Override
    public String toString() {
        return "AccommodationDetailsDTO{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", checkOutDate=" + checkOutDate +
                ", checkInDate=" + checkInDate +
                ", accommodationRequestNo='" + accommodationRequestNo + '\'' +
                ", bookingPurpose='" + bookingPurpose + '\'' +
                ", billingDetails='" + billingDetails + '\'' +
                ", travelDetails=" + travelDetails +
                ", previousId='" + previousId + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}