package com.example.TravelApp.Helpers;

import com.example.TravelApp.Model.TravelDetails;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;

@Service
public class HoursAndMoneyCounter {


    public Double countDurationInHours(TravelDetails firstTravel, TravelDetails lastTravel) {

        long intervalDays = ChronoUnit.DAYS.between(firstTravel.getDepartureDate().plusDays(1), lastTravel.getArrivalDate().minusDays(1));
        double hoursBetweenDays = intervalDays*24;
        hoursBetweenDays+=24-firstTravel.getDepartureTime().getHour();
        hoursBetweenDays+=lastTravel.getArrivalTime().getHour();

        int hoursFirstDay = (60 - firstTravel.getDepartureTime().getMinute());
        int hoursLastDay = lastTravel.getArrivalTime().getMinute();

        hoursBetweenDays +=Double.parseDouble(String.valueOf(hoursFirstDay)) / 60;
        hoursBetweenDays+=Double.parseDouble(String.valueOf(hoursLastDay)) / 60;

        return hoursBetweenDays;
    }

    public Double countDailyAllowance(TravelDetails firstTravel, TravelDetails lastTravel) {

        double numDailyAllowance = ChronoUnit.DAYS.between(firstTravel.getArrivalDate().plusDays(1), lastTravel.getDepartureDate().minusDays(1))+1;
        int firstDayHours=24-firstTravel.getArrivalTime().getHour();

        int lastDayHours=lastTravel.getDepartureTime().getHour();

        numDailyAllowance= firstDayHours>=12 ? numDailyAllowance+1 : numDailyAllowance;

        numDailyAllowance= lastDayHours>=12 ? numDailyAllowance+1 : numDailyAllowance;

        return numDailyAllowance;

    }
}
