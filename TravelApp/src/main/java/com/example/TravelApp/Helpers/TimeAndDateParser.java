package com.example.TravelApp.Helpers;

import com.example.TravelApp.Model.TravelDetails;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeAndDateParser {

    public static LocalDate parseDate(String arrivalDate) {

        LocalDate localDate = LocalDate.parse(arrivalDate);
        return localDate;
    }

    public static LocalDate parseDate2(String arrivalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDate localDate = LocalDate.parse(arrivalDate,formatter);
        return localDate;
    }

    public static LocalTime parseTime(String arrivalTime) {
        LocalTime localTime = LocalTime.parse(arrivalTime);
        return localTime;
    }

    public static LocalDateTime parseDateAndTime(String dateTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return localDateTime;
    }

    public static String getDateStringFormatted(String dateString) {
        String stringParts[] = dateString.split("-");
        String month = stringParts[1];
        String day = stringParts[2];
        String monthFixed=(month.length()==1) ? ("0"+month): month;
        String dayFixed=(day.length()==1) ? ("0"+day): day;

        return stringParts[0] + "-" +monthFixed + "-"+dayFixed;
    }

    public static LocalDate parseToLocalDate(String dateString) {
        String dateFormatted= TimeAndDateParser.getDateStringFormatted(dateString);
        LocalDate localDate = LocalDate.parse(dateFormatted);
        return localDate;
    }

    public static String currentDateString(){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd.yyyy");
        return localDate.format(formatter);
    }

    public static String getTimeString(LocalTime localTime){
       // System.out.println(localTime.toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
       // System.out.println(localTime.format(formatter));
        return localTime.format(formatter);
    }

    public static LocalDate currentDate(){
        LocalDate currentDate = LocalDate.now();
        return currentDate;
    }

    public static LocalTime currentTime(){
        LocalTime currentTime = LocalTime.now();
        return currentTime;
    }
    public static LocalDateTime parseLocalDateToLocalDateTime(LocalDate date) {
        return date.atTime(1,1,1);
    }

    public static LocalDateTime getStringLocalDateTime(LocalDate decisionDate) {
        String datum;
        String month = String.valueOf(decisionDate.getMonthValue());
        int year = decisionDate.getYear();
        String day = String.valueOf(decisionDate.getDayOfMonth());
        String monthFixed=(month.length()==1) ? ("0"+month): month;
        String dayFixed=(day.length()==1) ? ("0"+day): day;
        datum=year+monthFixed+dayFixed;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(datum, formatter).atStartOfDay();
    }

    // 2021-08-20 -> 20.08.2021
    public String transformDateString(LocalDate localDate) {
        String splitted[] = localDate.toString().split("-");
        return splitted[2] + "." + splitted[1] + "." + splitted[0] + ".";
    }

    public String getDateTimeAsStringFromTravelDetails(TravelDetails travelDetails) {
        LocalDate date = travelDetails.getDepartureDate();
        LocalTime time = travelDetails.getDepartureTime();

        return transformDateString(date) + "  " + time.toString();
    }

    public String getDateTimeAsStringFromTravelDetailsBorder(TravelDetails travelDetails, int num_hours) {
        LocalDate date = travelDetails.getDepartureDate();
        LocalTime time = travelDetails.getDepartureTime();
        int hours = time.getHour()+num_hours;
        int minutes = time.getMinute();
        if(hours<24) {
            String timeString = getStringAsTime(hours, minutes);
            return transformDateString(date) + "  " + timeString;
        } else {
            int hours_next_day = hours-24;
            return transformDateString(date.plusDays(1)) + "  " + hours_next_day+":"+time.getMinute();
        }
    }

    private String getStringAsTime(int hours, int minutes) {
        String hoursString="";
        String minutesString="";
        hoursString= hours <10 ? "0"+ hours : String.valueOf(hours);
        minutesString= minutes <10 ? "0"+ minutes : String.valueOf(minutes);
        return hoursString+":"+minutesString;
    }

    public String getDateTimeAsStringFromTravelDetailsBorderLast(TravelDetails travelDetails, int num_hours, int num_minutes) {
        LocalDate date = travelDetails.getDepartureDate();
        LocalTime time = travelDetails.getDepartureTime();

        int minutes = time.getMinute()-num_minutes;
        int hours = time.getHour()-num_hours;

        if(hours>0) {
            int minutes_updated =  60 + minutes;
            String timeString = getStringAsTime(hours, minutes_updated);
            return transformDateString(date) + "  " + timeString;
        } else {
            int hours_next_day = 24+hours;

            int minutes_next_day =  60 + minutes;
            String timeString = getStringAsTime(hours_next_day, minutes_next_day);

            return transformDateString(date.minusDays(1)) + "  " + timeString;
        }
    }
}

