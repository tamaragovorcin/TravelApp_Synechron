package com.example.TravelApp.Service.Implementations;

import com.example.TravelApp.DTOs.JsonTravelDataDTOs.JsonEmployeeTravelDetail;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Helpers.TimeAndDateParser;
import com.example.TravelApp.DTOs.UpdateTravelDataDTO;
import com.example.TravelApp.Mapper.AccommodationDetailsMapper;
import com.example.TravelApp.Mapper.TravelDetailsMapper;
import com.example.TravelApp.Model.TravelDetails;
import com.example.TravelApp.Repository.TravelDetailsRepository;
import com.example.TravelApp.Service.Interfaces.IAccommodationDetailsService;
import com.example.TravelApp.Service.Interfaces.ITravelDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TravelDetailsServiceImpl implements ITravelDetailsService, Comparable {

    @Autowired
    TravelDetailsRepository travelDetailsRepository;

    @Autowired
    TravelDetailsMapper travelDetailsMapper;

    @Autowired
    IAccommodationDetailsService accommodationDetailsService;

    @Autowired
    AccommodationDetailsMapper accommodationDetailsMapper;


    @Autowired
    TimeAndDateParser timeAndDateParser;

    @Override
    public Set<TravelDetailsDTO> findAll() {
        List<TravelDetails> travelDetailsList=  travelDetailsRepository.findAll();
        return travelDetailsMapper.getTravelDetailsDTOS(travelDetailsList);
    }

    @Override
    public TravelDetailsDTO save(TravelDetailsDTO travelDetailsDTO) {
        TravelDetails travelDetails =
                travelDetailsRepository.save(travelDetailsMapper.travelDetailsDTOToTravelDetails(travelDetailsDTO));
        TravelDetailsDTO detailsDTO = travelDetailsMapper.travelDetailsToDTO(travelDetails);
        return detailsDTO;
    }

    @Override
    public Set<TravelDetailsDTO> update(TravelDetailsDTO travelDetailsDTO) {

        TravelDetails travelDetails = travelDetailsRepository.findById(travelDetailsDTO.getId()).orElse(null);
       if(travelDetails != null){
            TravelDetails travelDetailsUpdated = travelDetailsMapper.travelDetailsDTOToTravelDetailsNoId(travelDetailsDTO);
            travelDetailsUpdated.setPreviousId(travelDetails.getId());
            travelDetailsUpdated.setUpdateDate(currentDate());
            travelDetailsUpdated.setUpdateTime(currentTime());
            travelDetailsUpdated.setAccommodationDetails(travelDetails.getAccommodationDetails());
            TravelDetailsDTO travelDetailsDTOUpdated = travelDetailsMapper.travelDetailsToDTO(travelDetailsRepository.save(travelDetailsUpdated));

            return updateTravelDetails(findAll());

        }
        return null;
    }

    @Override
    public void delete(long id) {

       travelDetailsRepository.delete(travelDetailsMapper.travelDetailsDTOToTravelDetails(findById(id)));


    }

    @Override
    public Set<TravelDetails> findByTravelReqID(String id) {
        return travelDetailsRepository.findByTravelReqID(id);
    }

    @Override
    public Set<TravelDetailsDTO> parseTravelDetails(List<JsonEmployeeTravelDetail> employee_travel_details) {
        List<TravelDetails> travelDetailsList = new ArrayList<>();
        for (JsonEmployeeTravelDetail jsonEmployeeTravelDetail: employee_travel_details) {
            Set<TravelDetails> travelDetailsByTravelReqID = findByTravelReqID(String.valueOf(jsonEmployeeTravelDetail.getTravelReqID()));
            if(travelDetailsByTravelReqID.size() == 0 || !alreadyExists(travelDetailsByTravelReqID, jsonEmployeeTravelDetail)) {
                TravelDetails travelDetails = travelDetailsMapper.jsonDetailsToTravelDetails(jsonEmployeeTravelDetail);
                travelDetailsList.add(travelDetailsRepository.save(travelDetails));
            }
        }
        return travelDetailsMapper.getTravelDetailsDTOS(travelDetailsList);
    }

    @Override
    public void isCircularTravel(String travelReqId) {
        Set<TravelDetails> travelDetails = this.travelDetailsRepository.findByTravelReqID(travelReqId);
        List<TravelDetails> travelDetailsList = new ArrayList<TravelDetails>();
        if(travelDetails.size() > 1){
            for(TravelDetails td : travelDetails){
                travelDetailsList.add(td);
            }
            setCircularTravel(travelDetailsList);
        }
    }

    @Override
    public TravelDetailsDTO findById(long id) {

        TravelDetails travelDetails = travelDetailsRepository.findById(id).orElse(null);
        TravelDetailsDTO travelDetailsDTO = travelDetailsMapper.travelDetailsToDTO(travelDetails);
        return travelDetailsDTO;
    }

  /*  @Override
    public void addAccommodationDetailsToTravelDetails(String travelReqID, Long accommodationRequestNo) {
        Set<TravelDetails> travelDetailsList = travelDetailsRepository.findByTravelReqID(travelReqID);
        for(TravelDetails travelDetails : travelDetailsList){
            travelDetails.setAccommodationDetails(accommodationDetailsService.findByIdNoDTO(accommodationRequestNo));
            travelDetailsRepository.save(travelDetails);
        }
    }*/


    @Override
    public void addAccommodationDetailsToTravelDetails(Long travelId, Long accommodationRequestNo) {
        TravelDetailsDTO travelDetails = findById(travelId);
        travelDetails.setAccommodationDetails(accommodationDetailsMapper.accommodationDetailsToDTO(accommodationDetailsService.findByIdNoDTO(accommodationRequestNo)));
        travelDetailsRepository.save(travelDetailsMapper.travelDetailsDTOToTravelDetails(travelDetails));

    }

    private void setCircularTravel(List<TravelDetails> travelDetailsList){
        Collections.sort(travelDetailsList, Comparator.comparing(TravelDetails::getDepartureDate));
        for(int i = 0 ;i < travelDetailsList.size(); i++){
            if(travelDetailsList.size() > i + 1)
                travelDetailsList.get(i).setReturnTrip(travelDetailsList.get(i+1).getDepartureDate().toString() + " " +travelDetailsList.get(i+1).getDepartureTime().toString());
                travelDetailsRepository.save(travelDetailsList.get(i));
        }
    }

    private boolean alreadyExists(Set<TravelDetails> travelDetailsByTravelReqID, JsonEmployeeTravelDetail jsonEmployeeTravelDetail) {
        boolean saveTrip = false;
        for (TravelDetails travelDetails : travelDetailsByTravelReqID) {
            if (travelDetails.getDepartureDate().isEqual(TimeAndDateParser.parseDate(jsonEmployeeTravelDetail.getDepartureDate()))) {
                if (travelDetails.getDepartureTime().equals(TimeAndDateParser.parseTime(jsonEmployeeTravelDetail.getDepartureTime()))) {
                    saveTrip = true;
                    break;
                }
            }
        }
        return saveTrip;
    }

    private String  setReturnTravel(TravelDetails travelDetailsFirst, TravelDetails travelDetailsSecond){
        if(travelDetailsFirst.getDepartureDate().isBefore(travelDetailsSecond.getDepartureDate())){
            travelDetailsFirst.setReturnTrip(travelDetailsSecond.getDepartureDate().toString());
            travelDetailsRepository.save(travelDetailsFirst);
            return travelDetailsFirst.getDepartureDate().toString();
        }else{
            travelDetailsSecond.setReturnTrip(travelDetailsFirst.getDepartureDate().toString());
            travelDetailsRepository.save(travelDetailsSecond);
            return travelDetailsSecond.getDepartureDate().toString();
        }
    }

    public Set<TravelDetailsDTO> updateTravelDetails(Set<TravelDetailsDTO> travelDetailsDTOLista){

        Set<TravelDetailsDTO> officialList = new HashSet<>();
        Set<TravelDetailsDTO> helperList = new HashSet<>();

        TravelDetailsDTO travelDetailsDTO = travelDetailsDTOLista.stream().findFirst().get();
        UpdateTravelDataDTO updateTravelDataDTO = findLatestUpdate(travelDetailsDTO, travelDetailsDTOLista);

        // travelDetailsDTO je izabran, a list je svi ostali.
        if(updateTravelDataDTO!=null) {
            officialList.add(updateTravelDataDTO.getTravelDetailsDTO());
            for (TravelDetailsDTO travelDetailsDTO1 : updateTravelDataDTO.getList()) {
                helperList.add(travelDetailsDTO1);
            }
        }

        Set<TravelDetailsDTO> notChosen = elementsLeft(helperList, travelDetailsDTOLista);

        while(notChosen.size()!=0){
            travelDetailsDTO = notChosen.stream().findFirst().get();
            updateTravelDataDTO = findLatestUpdate(travelDetailsDTO, travelDetailsDTOLista);
            if(updateTravelDataDTO!=null) {

                Boolean help = false;
                for(TravelDetailsDTO travelDetailsDTO1: officialList){
                    if(updateTravelDataDTO.getTravelDetailsDTO().getId() == travelDetailsDTO1.getId()){
                        help = true;

                    }
                }
                if(!help){
                    officialList.add(updateTravelDataDTO.getTravelDetailsDTO());
                }

                for (TravelDetailsDTO travelDetailsDTO1 : updateTravelDataDTO.getList()) {
                    helperList.add(travelDetailsDTO1);
                }
            }

            notChosen = elementsLeft(helperList, travelDetailsDTOLista);
        }

        return officialList;
    }

    private Set<TravelDetailsDTO> elementsLeft(   Set<TravelDetailsDTO> helperList, Set<TravelDetailsDTO> travelDetailsDTOLista){

        Set<TravelDetailsDTO> notChosen = new HashSet<>();
        Boolean help = false;
        for (TravelDetailsDTO travelDetails3 : travelDetailsDTOLista) {
            for (TravelDetailsDTO travelDetails2 : helperList) {
                if (travelDetails2.getId() == travelDetails3.getId()) {
                    help = true;
                }
            }
            if (!help) {
                notChosen.add(travelDetails3);
            }
            help = false;
        }

        return notChosen;
    }

    private TravelDetailsDTO findTravelDetails(long id, Set<TravelDetailsDTO> travelDetailsDTOLista){

        TravelDetailsDTO travelDetailsDTO = null;
        for(TravelDetailsDTO travelDetails2: travelDetailsDTOLista) {
            if (travelDetails2.getPreviousId() != 0 && travelDetails2.getPreviousId() == id) {
                travelDetailsDTO = travelDetails2;
            }
        }
        return travelDetailsDTO;
    }


    private UpdateTravelDataDTO findLatestUpdate(TravelDetailsDTO firstElement, Set<TravelDetailsDTO> travelDetailsDTOLista){

        Set<TravelDetailsDTO> updatedList = new HashSet<>();
        Set<TravelDetailsDTO> allUpdateList = new HashSet<>();
        UpdateTravelDataDTO updateTravelDataDTO = new UpdateTravelDataDTO();

        TravelDetailsDTO returned = findTravelDetails(firstElement.getId(), travelDetailsDTOLista);
        if(returned == null && firstElement.getPreviousId()==0){

            allUpdateList.add(firstElement);
            updateTravelDataDTO.setTravelDetailsDTO(firstElement);
            updateTravelDataDTO.setList(allUpdateList);
            return updateTravelDataDTO;
        }

        else if(returned == null && firstElement.getPreviousId()!=0){
            return null;
        }

        while (returned != null) {
            updatedList.add(returned);
            returned = findTravelDetails(returned.getId(),travelDetailsDTOLista);
        }

        updatedList.add(firstElement);

        TravelDetailsDTO travelDetailsChosen = new TravelDetailsDTO();

        long id = updatedList.stream().findFirst().get().getId();

        for (TravelDetailsDTO travelDetailsFromUpdatedList : updatedList) {

            if(travelDetailsFromUpdatedList.getId() >= id){
                travelDetailsChosen = travelDetailsFromUpdatedList;
                id = travelDetailsFromUpdatedList.getId();
            }


        }
        updateTravelDataDTO.setTravelDetailsDTO(travelDetailsChosen);
        updateTravelDataDTO.setList(updatedList);
        return updateTravelDataDTO;
    }


    private String currentDate(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = currentDate.format(formatter);
        return date;
    }

    private String currentTime(){
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        String time = currentTime.format(formatter);
        return time;
    }



    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
