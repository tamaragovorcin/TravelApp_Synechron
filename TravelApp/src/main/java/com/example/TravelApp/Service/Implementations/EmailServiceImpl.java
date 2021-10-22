package com.example.TravelApp.Service.Implementations;

import com.example.TravelApp.DTOs.EmailDTO;
import com.example.TravelApp.Service.Interfaces.IFilesService;
import com.example.TravelApp.Model.TravelDetails;
import com.example.TravelApp.Repository.TravelDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    @Autowired
    private IFilesService filesService;

    @Autowired
    private TravelDetailsRepository travelDetailsRepository;

    @Autowired
    TravelDetailsServiceImpl travelDetailsService;

    @Async
    public void sendReservationEmail(EmailDTO transferDetails){
        SimpleMailMessage message = new SimpleMailMessage();
        //message.setTo(transferDetails.getEmployee().getEmail());
        message.setTo("strahinjacvijanovic@gmail.com");
        message.setFrom("HR-Serbia@synechron.com");
        message.setSubject("Transfer Reserved");
        message.setText("Hello " + transferDetails.getEmployee().getFirstName() + "\nYour transfer to "+transferDetails.getDepartFrom()+
                " is successfully reserved\n."+
                transferDetails.getReservationTime().toString()+". \nBest regards, Synechron\n");
        javaMailSender.send(message);
    }

    @Async
    public void sendDecisionAndOrderEmail(EmailDTO emailDTO) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            FileSystemResource file  = new FileSystemResource(new File(emailDTO.getFilepath()));
            helper.addAttachment(file.getFilename(), file);
            helper.setTo("tamaragovorcin99@gmail.com");
            helper.setSubject("Decision and order for a business trip");
            helper.setText("Hello " + "\nDecision and order for a business trip are created. You’ll find the attachment below."+ "\n\nBest regards,\n Synechron");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    @Async
    public void sendCalculationEmail(EmailDTO emailDTO) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        List<String> allFilePaths = filesService.loadAllFilePaths(emailDTO.getTravelReqId());
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            FileSystemResource file  = new FileSystemResource(new File(emailDTO.getFilepath()));
            helper.addAttachment(file.getFilename(), file);

            for (String oneFilePath: allFilePaths) {
                FileSystemResource oneFile  = new FileSystemResource(new File(oneFilePath));
                helper.addAttachment(oneFile.getFilename(), oneFile);
            }

            helper.setTo("tamaragovorcin99@gmail.com");
            helper.setSubject("Decision, order and calculation for a business trip");
            helper.setText("Hello " + "\nDecision, order and calculation for a business trip are created. You’ll find the attachment below."+ "\n\nBest regards,\n Synechron");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void reserveTransferForUpcomingTrip(EmailDTO travelDetailsDTOListCheck){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("strahinjacvijanovic@gmail.com");
        message.setFrom("HR-Serbia@synechron.com");
        message.setSubject("Reserve transportation for upcoming trip");
        String body = "Dear,\n " + "\nPlease reserve the transportation for upcoming trip(s): \n";
        for (int i = 0; i < travelDetailsDTOListCheck.getTravelReqIDList().size(); i++) {


            body += "Travel id: " + travelDetailsDTOListCheck.getTravelReqIDList().get(i) + "\n" +
                    "Departure date: " + travelDetailsDTOListCheck.getDepartureDateList().get(i) + "\n" +
                    "Departure time: " + travelDetailsDTOListCheck.getDepartureTimeList().get(i) + "\n" +
                    "Departing from: " + travelDetailsDTOListCheck.getDepartFromList().get(i) + "\n" +
                    "Arrival date: " + travelDetailsDTOListCheck.getArrivalDateList().get(i) + "\n" +
                    "Arrival time: " + travelDetailsDTOListCheck.getArrivalTimeList().get(i) + "\n" +
                    "Arrival country: " + travelDetailsDTOListCheck.getArrivalCountryList().get(i) +
                    "\n----------------------------------------------------------------------------------------------" +
                    "\n\n\n" +
                    "Best regards, Synechron";
        }
        message.setText(body);
        javaMailSender.send(message);
    }

    @Async
    public void reserveTransporationForUpcomingTrip(EmailDTO travelDetailsDTOListNotReserved){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("strahinjacvijanovic@gmail.com");
        message.setFrom("HR-Serbia@synechron.com");
        message.setSubject("Check transportation for upcoming trip");
        String body = "Dear,\n " + "\nPlease make sure you reserved a proper transportation for the upcoming trip(s): \n";
        for (int i = 0; i < travelDetailsDTOListNotReserved.getTravelReqIDList().size(); i++) {


            body += "Travel id: " + travelDetailsDTOListNotReserved.getTravelReqIDList().get(i) + "\n" +
                    "Departure date: " + travelDetailsDTOListNotReserved.getDepartureDateList().get(i) + "\n" +
                    "Departure time: " + travelDetailsDTOListNotReserved.getDepartureTimeList().get(i) + "\n" +
                    "Departing from: " + travelDetailsDTOListNotReserved.getDepartFromList().get(i) + "\n" +
                    "Arrival date: " + travelDetailsDTOListNotReserved.getArrivalDateList().get(i) + "\n" +
                    "Arrival time: " + travelDetailsDTOListNotReserved.getArrivalTimeList().get(i) + "\n" +
                    "Arrival country: " + travelDetailsDTOListNotReserved.getArrivalCountryList().get(i) +
                    "\n----------------------------------------------------------------------------------------------" +
                    "\n\n\n" +
                    "Best regards, Synechron";
        }
        message.setText(body);
        javaMailSender.send(message);
    }

    @Async
    public void advancePaymentNotification(EmailDTO decisionForABusinessTrips){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("strahinjacvijanovic@gmail.com");
        message.setFrom("HR-Serbia@synechron.com");
        message.setSubject("Advance payment for the upcoming trip");
        String body = "Dear,\n " + "\nPlease make sure advance payment is paid for the upcoming trip(s): \n";
        for (int i = 0; i < decisionForABusinessTrips.getTravelReqIDList().size(); i++) {
            body += "Travel id: " + decisionForABusinessTrips.getTravelReqIDList().get(i) + "\n" +
                    "Employee: " + decisionForABusinessTrips.getEmployeeFullNameList().get(i)+ "\n" +
                    "Advance payment: " + decisionForABusinessTrips.getAdvancePaymentList().get(i) +
                    "\n----------------------------------------------------------------------------------------------" +
                    "\n\n\n" +
                    "Best regards, Synechron";
        }
        message.setText(body);
        javaMailSender.send(message);
    }

    @Async
    public void payOffNotification(EmailDTO arrivalsSoon){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("strahinjacvijanovic@gmail.com");
        message.setFrom("HR-Serbia@synechron.com");
        message.setSubject("Prepare payoff for the trip");
        String body = "Dear,\n " + "\nPlease prepare payoff in the next two days for the trip(s): \n";
        for (int i = 0; i < arrivalsSoon.getTravelReqIDList().size(); i++) {

            TravelDetails travelDetails = travelDetailsRepository.findById(arrivalsSoon.getIds().get(i)).orElse(null);
            if(travelDetails != null){
                travelDetails.setMailSent(true);
                travelDetailsRepository.save(travelDetails);

                body += "Travel id: " + arrivalsSoon.getTravelReqIDList().get(i) + "\n" +
                        "Arrival date: " + arrivalsSoon.getArrivalDateList().get(i) + "\n" +
                        "Arrival time: " + arrivalsSoon.getArrivalTimeList().get(i) + "\n" +
                        "Arrival country: " + arrivalsSoon.getArrivalCountryList().get(i) +
                        "\n----------------------------------------------------------------------------------------------" +
                        "\n\n\n" +
                        "Best regards, Synechron";
            }
        }
        message.setText(body);
        javaMailSender.send(message);
    }
}
