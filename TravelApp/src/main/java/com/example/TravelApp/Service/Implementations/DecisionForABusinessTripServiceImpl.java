package com.example.TravelApp.Service.Implementations;

import com.example.TravelApp.DTOs.EmailDTO;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.ExelFiles.FileCreators.CalculationForABusinessTripCreator;
import com.example.TravelApp.ExelFiles.FileCreators.DecisionForABusinessTripCreator;
import com.example.TravelApp.Helpers.SerializerAndDeserializer;
import com.example.TravelApp.Mapper.DecisionForABusinessTripMapper;
import com.example.TravelApp.Mapper.EmailMapper;
import com.example.TravelApp.Model.DecisionForABusinessTrip;
import com.example.TravelApp.Repository.DecisionForABusinessTripRepository;
import com.example.TravelApp.Service.Interfaces.IDecisionForABusinessTripService;
import com.example.TravelApp.Service.Interfaces.IDecisionTripInfoService;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

@Service
public class DecisionForABusinessTripServiceImpl implements IDecisionForABusinessTripService {

    @Autowired
    DecisionForABusinessTripRepository decisionForABusinessTripRepository;

    @Autowired
    DecisionForABusinessTripMapper decisionForABusinessTripMapper;

    @Autowired
    DecisionForABusinessTripCreator decisionForABusinessTripCreator;

    @Autowired
    CalculationForABusinessTripCreator calculationForABusinessTripCreator;

    @Autowired
    IDecisionTripInfoService decisionTripInfoService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private EmailMapper emailMapper;

    @Override
    public DecisionForABusinessTrip save(List<TravelDetailsDTO> travelDetailsDTList) {
        DecisionForABusinessTrip decisionForABusinessTrip = decisionForABusinessTripMapper.travelDetailsDTOtoDecision(travelDetailsDTList);
        DecisionForABusinessTrip savedDecision = decisionForABusinessTripRepository.save(decisionForABusinessTrip);
        decisionTripInfoService.saveAll(decisionForABusinessTrip.getDecisionTripInfo(), savedDecision);
        String filepath = decisionForABusinessTripCreator.createExelFileFromDecision(decisionForABusinessTrip);

        EmailDTO emailDTO = emailMapper.emailDtoFromFilepath(filepath);
        sendEmailWithFile(emailDTO, "sendDecisionAndOrderEmail");
        return savedDecision;
    }

    private void sendEmailWithFile(EmailDTO emailDTO, String sendDecisionAndOrderEmail) {
        emailDTO.setMessage(sendDecisionAndOrderEmail);
        try {
            String messageObj = SerializerAndDeserializer.toString(emailDTO);
            jmsTemplate.convertAndSend(queue, messageObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DecisionForABusinessTrip> findAll() {
        return decisionForABusinessTripRepository.findAll();
    }


    @Override
    public List<DecisionForABusinessTrip> saveAll(Set<TravelDetailsDTO> travelDetailsDTOs) {
        HashMap<String, List<TravelDetailsDTO>> decisionsHashMap = sortTravelDetailsDtoInMap(travelDetailsDTOs);
        Iterator <String> it = decisionsHashMap.keySet().iterator();
        List<DecisionForABusinessTrip> decisionForABusinessTrips = new ArrayList<>();
        while(it.hasNext())
        {
            decisionForABusinessTrips.add(save(decisionsHashMap.get(it.next())));
        }
        return decisionForABusinessTrips;
    }

    private  HashMap<String, List<TravelDetailsDTO>> sortTravelDetailsDtoInMap(Set<TravelDetailsDTO> travelDetailsDTOs) {
        HashMap<String, List<TravelDetailsDTO>> decisionsHashMap = new HashMap<>();
        for (TravelDetailsDTO travelDetailsDTO: travelDetailsDTOs) {
            if(decisionsHashMap.containsKey(travelDetailsDTO.getTravelReqID())) {
                decisionsHashMap.get(travelDetailsDTO.getTravelReqID()).add(travelDetailsDTO);
            }
            else {
                List<TravelDetailsDTO> travelDetailsDTOS = new ArrayList<>();
                travelDetailsDTOS.add(travelDetailsDTO);
                decisionsHashMap.put(travelDetailsDTO.getTravelReqID(),travelDetailsDTOS);
            }
        }
        return decisionsHashMap;
    }

    public void scheduleDecisionSaving(Set<TravelDetailsDTO> travelDetailsDTOS) {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        saveAll(travelDetailsDTOS);
                    }
                },
                5000
        );
    }

    @Override
    public byte[] exportFileByDecisionId(String travelReqId)  {
        DecisionForABusinessTrip decision = findByTravelReqID(travelReqId);
        String filePath = "src/main/java/com/example/TravelApp/ExelFiles/DecisionForABusinessTrip/"+decision.getDecisionNumber() + ".xlsx";
        return getFileByteArr(filePath);
    }

    @Override
    public DecisionForABusinessTrip findByTravelReqID(String travelReqID) {
        return decisionForABusinessTripRepository.findByTravelReqID(travelReqID);
    }

    public byte[] getFileByteArr(String fileName) {
        try {
            InputStream inputStream = new FileInputStream(fileName);
            try (XSSFWorkbook workbook =(XSSFWorkbook) WorkbookFactory.create(inputStream)) {
                try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                    workbook.write(bos);
                    workbook.close();
                    inputStream.close();
                    return bos.toByteArray();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[1024];
    }

    @Override
    public String findNextDecisionNumber() {
        LocalDate localDate = LocalDate.now();
        String year = String.valueOf(localDate.getYear());
        List<DecisionForABusinessTrip> decisionForABusinessTrips = findAll();

        long maxNumber = getMaxNumber(year, decisionForABusinessTrips);
        return maxNumber + 1 + "-" + year;
    }

    public long getMaxNumber(String year, List<DecisionForABusinessTrip> decisionForABusinessTrips) {
        long maxNumber = 0L;
        for (DecisionForABusinessTrip decisionForABusinessTrip: decisionForABusinessTrips) {
            String yearInDecision = decisionForABusinessTrip.getDecisionNumber().split("-")[1];
            if(yearInDecision.equals(year)) {
                String number = decisionForABusinessTrip.getDecisionNumber().split("-")[0];
                maxNumber = Math.max(Long.parseLong(number), maxNumber);
            }

        }
        return maxNumber;
    }

    @Override
    public byte[] exportFileWithCalculationByTravelReqId(String travelReqId) {
        DecisionForABusinessTrip decisionForABusinessTrip = findByTravelReqID(travelReqId);
        String filePath = "src/main/java/com/example/TravelApp/ExelFiles/DecisionForABusinessTrip/"+decisionForABusinessTrip.getDecisionNumber() + ".xlsx";
        return createCalculation(decisionForABusinessTrip, filePath);
    }

    public byte[] createCalculation(DecisionForABusinessTrip decisionForABusinessTrip, String filePath) {
        calculationForABusinessTripCreator.createCalculation(decisionForABusinessTrip, filePath);

        EmailDTO emailDTO = emailMapper.emailDtoFromFilepathAndTravelReqId(filePath, decisionForABusinessTrip.getTravelReqID());
        sendEmailWithFile(emailDTO, "sendCalculationEmail");

        return getFileByteArr(filePath);
    }
}
