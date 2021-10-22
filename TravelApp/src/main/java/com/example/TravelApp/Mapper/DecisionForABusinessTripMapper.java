package com.example.TravelApp.Mapper;

import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Helpers.TimeAndDateParser;
import com.example.TravelApp.Model.*;
import com.example.TravelApp.Service.Implementations.NBSServiceImpl;
import com.example.TravelApp.Service.Interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class DecisionForABusinessTripMapper {
    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IDecisionForABusinessTripService decisionForABusinessTripService;

    @Autowired
    ICountryAndCityService countryAndCityService;

    @Autowired
    ITravelDetailsService travelDetailsService;

    @Autowired
    NBSServiceImpl nbsService;

    public DecisionForABusinessTrip travelDetailsDTOtoDecision(List<TravelDetailsDTO> travelDetailsDTList) {

        double travelDuration = countTravelDuration(travelDetailsDTList.get(0).getTravelReqID());

        DecisionForABusinessTrip decisionForABusinessTrip = getDecisionForABusinessTrip(travelDetailsDTList, travelDuration);

        double dailyAllowance = 50.00;
        String currency = "EUR";
        List<DecisionTripInfo> decisionTripInfoList = new ArrayList<>(decisionForABusinessTrip.getDecisionTripInfo());
        decisionTripInfoList.sort(Comparator.comparing(p -> TimeAndDateParser.parseDate2(p.getDepartureDate())));
        String arrivalCountry = decisionTripInfoList.get(0).getArrivalCountry();
        switch (arrivalCountry) {
            case "Mađarska":
                dailyAllowance = 40.00;
                break;
            case "Švajcarska":
                dailyAllowance = 70.00;
                currency = "CHF";
                break;
            case "Velika Britanija":
                dailyAllowance = 50.00;
                currency = "GBP";
                break;
            case "SAD":
                dailyAllowance = 60.00;
                currency = "USD";
                break;
            case "Indija":
                dailyAllowance = 40.00;
                break;
            case "Dubai":
                dailyAllowance = 60.00;
                currency = "USD";
                break;
            default:
                break;
        }
        LocalDateTime date = TimeAndDateParser.getStringLocalDateTime(decisionForABusinessTrip.getDecisionDate());

        double dailyAllowanceEUR =dailyAllowance;

        double middleCourseForEUR = nbsService.getMiddleCourseForDate(date, "EUR");
        if (!currency.equals("EUR")) {
            double middleCourseCustomCurrency = nbsService.getMiddleCourseForDate(date, currency);
            double dailyAllowanceRSD = dailyAllowance * middleCourseCustomCurrency;
            dailyAllowanceEUR = dailyAllowanceRSD / middleCourseForEUR;
        }
        double advancePaymentEUR = 0.00;
        if(travelDuration!=0) {
            advancePaymentEUR = (travelDuration*dailyAllowanceEUR>=500.00) ? 0.8 * dailyAllowanceEUR * travelDuration : 0.00;
        }

        decisionForABusinessTrip.setAdvancePaymentRSD(Math.floor(0.00));
        decisionForABusinessTrip.setAdvancePaymentEUR(Math.floor(advancePaymentEUR*100)/100);
        decisionForABusinessTrip.setDailyAllowanceEUR(Math.floor(dailyAllowanceEUR*100)/100);
        decisionForABusinessTrip.setDailyAllowanceRSD(Math.floor((2425.00*100)/100));

        return decisionForABusinessTrip;
    }

    private DecisionForABusinessTrip getDecisionForABusinessTrip(List<TravelDetailsDTO> travelDetailsDTOList, double travelDuration) {
        TravelDetailsDTO travelDetailsDTO = travelDetailsDTOList.get(0);
        String employeePosition = employeeService.findPositionName(travelDetailsDTO.getEmployee().getEmployeeId());
        String travelDurationString = (travelDuration !=0) ? travelDuration + " dana" :"nepoznatno";

        DecisionForABusinessTrip decisionForABusinessTrip = new DecisionForABusinessTrip();

        decisionForABusinessTrip.setDecisionNumber(decisionForABusinessTripService.findNextDecisionNumber());
        decisionForABusinessTrip.setDecisionDate(LocalDate.now());
        decisionForABusinessTrip.setAuthorizedPerson("direktor Davorin Zrnić");
        decisionForABusinessTrip.setTheBasisOfAuthorization("člana 15. Odluke o osnivanju");
        decisionForABusinessTrip.setEmployeeFullName(travelDetailsDTO.getEmployee().getFirstName() + " " + travelDetailsDTO.getEmployee().getLastName());
        decisionForABusinessTrip.setEmployeeDesignationName(employeePosition);
        decisionForABusinessTrip.setTravelPurpose(getTranslatedBookingPurpose(travelDetailsDTO.getBookingPurpose()));
        
        decisionForABusinessTrip.setTravelDuration(travelDurationString);
        decisionForABusinessTrip.setWaysOfTransport("sva prevozna sredstva");
        decisionForABusinessTrip.setFreeAccommodationAndFoodOnATrip("nisu obezbedjeni");
        decisionForABusinessTrip.setTravelReqID(travelDetailsDTO.getTravelReqID());


        Set<DecisionTripInfo> decisionTripInfoSet = getDecisionTripInfoList(travelDetailsDTOList);
        decisionForABusinessTrip.setDecisionTripInfo(decisionTripInfoSet);

        
        return decisionForABusinessTrip;
    }

    private Set<DecisionTripInfo> getDecisionTripInfoList(List<TravelDetailsDTO> travelDetailsDTOList) {

        Set<DecisionTripInfo> decisionTripInfoSet = new HashSet<>();
        for (TravelDetailsDTO travelDetailsDTO1: travelDetailsDTOList) {
            CountryAndCity countryAndCity = countryAndCityService.findByCountryAndCity(travelDetailsDTO1.getArrivalCountry(), travelDetailsDTO1.getArriveTo());
            DecisionTripInfo decisionTripInfo = new DecisionTripInfo();
            if(countryAndCity!=null) {
                decisionTripInfo.setArrivalCity(countryAndCity.getGrad());
                decisionTripInfo.setArrivalCountry(countryAndCity.getDrzava());
            }
            else {
                decisionTripInfo.setArrivalCity(travelDetailsDTO1.getArriveTo());
                decisionTripInfo.setArrivalCountry(travelDetailsDTO1.getArrivalCountry());
            }

            decisionTripInfo.setDepartureDate(travelDetailsDTO1.getDepartureDate());
            decisionTripInfoSet.add(decisionTripInfo);

        }
        return decisionTripInfoSet;
    }


    private String getTranslatedBookingPurpose(String bookingPurpose) {
        switch (bookingPurpose) {
            case "Events":
                return "Događaji";
            case "Business Visit":
                return "Poslovna poseta";
            default:
                return "";
        }
    }

    private double countTravelDuration(String travelRequestId) {
        Set<TravelDetails> travelDetailsSet = travelDetailsService.findByTravelReqID(travelRequestId);

        if(travelDetailsSet.size()>1) {
            List<TravelDetails> travelDetailsList = new ArrayList<>(travelDetailsSet);
            travelDetailsList.sort(Comparator.comparing(TravelDetails::getDepartureDate));
            TravelDetails first = travelDetailsList.get(0);
            TravelDetails last = travelDetailsList.get(travelDetailsList.size()-1);
            return getDaysBetweenTwoFlights(first, last);
        }
        else { return 0; }

    }

    private long getDaysBetweenTwoFlights(TravelDetails first, TravelDetails last) {
        AccommodationDetails accommodationDetails = first.getAccommodationDetails();
        if(accommodationDetails!=null) {
            if(DAYS.between(accommodationDetails.getCheckOutDate().toLocalDate(), last.getArrivalDate())>2) {
                return DAYS.between(first.getDepartureDate(),accommodationDetails.getCheckOutDate());
            }
            else {
                return DAYS.between(first.getDepartureDate(), last.getArrivalDate());
            }
        }else {
            return DAYS.between(first.getDepartureDate(), last.getArrivalDate());
        }
    }
}
