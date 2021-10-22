package com.example.TravelApp.Service.Interfaces;

import java.time.LocalDateTime;

public interface INBSService {
    double getMiddleCourseForDate(LocalDateTime localDateTime, String currency);
}
