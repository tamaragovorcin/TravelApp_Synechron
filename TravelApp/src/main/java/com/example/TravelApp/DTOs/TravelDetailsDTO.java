package com.example.TravelApp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelDetailsDTO {
    private long id;
    private String departFrom;
    private String arriveTo;
    private String departureDate;
    private String travelReqID;
    private String accountName;
    private String approvedBy;
    private EmployeeDTO employee;
    private String visaDetail;
    private String tripPaidBy;
    private String arrivalTime;
    private String arrivalDate;
    private String departureTime;
    private String departingCountry;
    private String arrivalCountry;
    private String additionalComments;
    private String bookingPurpose;
    private String partOfTheDayFrom;
    private String partOfTheDayTo;
    private AccommodationDetailsDTO accommodationDetails;
    private long previousId;
    private String updateDate;
    private String updateTime;
    private String returnTrip;
    private long accommodationId;
    private Boolean mailSent;

    @Override
    public String toString() {
        return "TravelDetailsDTO{" +
                "id=" + id +
                ", departFrom='" + departFrom + '\'' +
                ", arriveTo='" + arriveTo + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", travelReqID='" + travelReqID + '\'' +
                ", accountName='" + accountName + '\'' +
                ", approvedBy='" + approvedBy + '\'' +
                ", employee=" + employee +
                ", visaDetail='" + visaDetail + '\'' +
                ", tripPaidBy='" + tripPaidBy + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", departureTime=" + departureTime +
                ", departingCountry='" + departingCountry + '\'' +
                ", arrivalCountry='" + arrivalCountry + '\'' +
                ", additionalComments='" + additionalComments + '\'' +
                ", bookingPurpose='" + bookingPurpose + '\'' +
                ", partOfTheDayFrom=" + partOfTheDayFrom +
                ", partOfTheDayTo=" + partOfTheDayTo +
                ", accommodationDetails=" + accommodationDetails +
                ", previousId='" + previousId + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", returnTrip='" + returnTrip + '\'' +
                '}';
    }
}