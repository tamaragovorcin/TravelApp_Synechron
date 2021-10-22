package com.example.TravelApp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTravelDataDTO {

   private TravelDetailsDTO travelDetailsDTO;

   private Set<TravelDetailsDTO> list;

   @Override
   public String toString() {
      return "UpdateTravelDataDTO{" +
              "travelDetailsDTO=" + travelDetailsDTO +
              ", list=" + list +
              '}';
   }
}
