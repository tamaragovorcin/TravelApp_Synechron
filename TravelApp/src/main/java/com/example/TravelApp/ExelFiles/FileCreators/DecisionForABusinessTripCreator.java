package com.example.TravelApp.ExelFiles.FileCreators;

import com.example.TravelApp.Helpers.TimeAndDateParser;
import com.example.TravelApp.Model.DecisionForABusinessTrip;
import com.example.TravelApp.Model.DecisionTripInfo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PropertyTemplate;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DecisionForABusinessTripCreator {

    @Autowired
    TimeAndDateParser timeAndDateParser;

    @Autowired
    CellStyleCreator cellStyleCreator;

    @Autowired
    OrderForABusinessTripCreator orderForABusinessTripCreator;

    private static int row_counter = 0;
    private static int flights_number = 0;


    public String createExelFileFromDecision(DecisionForABusinessTrip decisionForABusinessTrip) {
        String filePath = "src/main/java/com/example/TravelApp/ExelFiles/DecisionForABusinessTrip/"+decisionForABusinessTrip.getDecisionNumber() + ".xlsx";
        try {
            XSSFWorkbook workbook = getDocumentContent(decisionForABusinessTrip);
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            workbook.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        row_counter = 0;
        return filePath;
    }

    private XSSFWorkbook getDocumentContent(DecisionForABusinessTrip decisionForABusinessTrip) {
        PropertyTemplate propertyTemplate = new PropertyTemplate();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet decisionSheet = workbook.createSheet("Odluka SPI");
        createBasicInformationTable(decisionForABusinessTrip, propertyTemplate, workbook, decisionSheet);
        createTable(decisionForABusinessTrip, propertyTemplate, workbook, decisionSheet);
        createTableForSignature(decisionForABusinessTrip.getDecisionDate(), workbook, decisionSheet);

        propertyTemplate.applyBorders(decisionSheet);

        XSSFSheet orderSheet = workbook.createSheet("Nalog SPI");
        orderForABusinessTripCreator.createContentForOrder(orderSheet,workbook,decisionForABusinessTrip);
        return workbook;
    }
    private void createBasicInformationTable(DecisionForABusinessTrip decisionForABusinessTrip, PropertyTemplate propertyTemplate, XSSFWorkbook workbook, XSSFSheet sheet) {

        createSynechronTitle(decisionForABusinessTrip, workbook, sheet);

        createBasicInformation(decisionForABusinessTrip, workbook, sheet);

        createDocumentPurpose(workbook, sheet);

        mergeAndBorderBasicInformationTable(propertyTemplate, sheet);

    }

    private void mergeAndBorderBasicInformationTable(PropertyTemplate propertyTemplate, XSSFSheet sheet) {
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 1, 6));
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 7, 10));

        sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 6));

        sheet.addMergedRegion(new CellRangeAddress(5, 5, 7, 10));
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 10));


        sheet.addMergedRegion(new CellRangeAddress(10, 10, 1, 10));
        sheet.addMergedRegion(new CellRangeAddress(11, 11, 1, 10));
        sheet.addMergedRegion(new CellRangeAddress(12, 12, 1, 10));

        propertyTemplate.drawBorders(new CellRangeAddress(4, 4, 1, 10), BorderStyle.DOTTED, BorderExtent.OUTSIDE); //outside border around A4:A9
        propertyTemplate.drawBorders(new CellRangeAddress(5, 5, 1, 10), BorderStyle.DOTTED, BorderExtent.OUTSIDE); //outside border around A4:A9
        propertyTemplate.drawBorders(new CellRangeAddress(6, 6, 1, 10), BorderStyle.DOTTED, BorderExtent.OUTSIDE); //outside border around A4:A9
    }

    private void createDocumentPurpose(XSSFWorkbook workbook, XSSFSheet sheet) {

        getOneRowOfPurpose(workbook, sheet, "ODLUKU");
        getOneRowOfPurpose(workbook, sheet, "O UPUĆIVANJU ZAPOSLENOG");
        getOneRowOfPurpose(workbook, sheet, "NA SLUŽBENI PUT U INOSTRANSTVO");

        sheet.createRow(row_counter++);
    }

    private void getOneRowOfPurpose(XSSFWorkbook workbook, XSSFSheet sheet, String title) {
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c ++);
        Cell cell = row.createCell(c ++);
        cell.setCellValue(title);
        cell.setCellStyle(cellStyleCreator.getCenteredBoldCellStyle(workbook));
        createMultipleCells(row, c , 8);
    }

    private void createBasicInformation(DecisionForABusinessTrip decisionForABusinessTrip, XSSFWorkbook workbook, XSSFSheet sheet) {
        CellStyle cellStyle = cellStyleCreator.getRightGreenBoldItalicCellStyle(workbook);

        CellStyle cellStyle2 = cellStyleCreator.getRightWithGreenBackgroundCellStyle(workbook);

        createOneRowInBasicTable("Ovlašćeno lice",decisionForABusinessTrip.getAuthorizedPerson(), sheet,cellStyle);
        createOneRowInBasicTable("na osnovu ovlašćenja iz",decisionForABusinessTrip.getTheBasisOfAuthorization(), sheet,cellStyle2);

        Row row3 = sheet.createRow(row_counter++);
        int c = 0;
        row3.createCell(c++);
        Cell cell30 = row3.createCell(c++);
        String formattedDate = timeAndDateParser.transformDateString(decisionForABusinessTrip.getDecisionDate());
        cell30.setCellValue("       dana " + formattedDate + " godine donosi sledeću");
        createMultipleCells(row3, c,8);

        sheet.createRow(row_counter++);
        sheet.createRow(row_counter++);
        sheet.createRow(row_counter++);
    }

    private Row createOneRowInBasicTable(String title,String content, XSSFSheet sheet,CellStyle cellStyle) {
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);
        Cell cell = row.createCell(c++);
        cell.setCellValue("        " + title);

        c=createMultipleCells(row,c,5);

        Cell cell1 = row.createCell(c++);
        cell1.setCellValue(content);
        cell1.setCellStyle(cellStyle);

        createMultipleCells(row,c,3);
        return row;
    }

    private void createSynechronTitle(DecisionForABusinessTrip decisionForABusinessTrip, XSSFWorkbook workbook, XSSFSheet sheet) {
        sheet.createRow(row_counter++);
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);

        Cell cell = row.createCell(c++);
        cell.setCellValue("SYNECHRON SRB doo, Novi Sad");
        cell.setCellStyle(cellStyleCreator.getLeftGreenBoldUnderlinedCellStyle(workbook));
        c = createMultipleCells(row, c,7);
        
        Cell cell9 = row.createCell(c++);
        cell9.setCellValue("Broj:");
        cell9.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));

        Cell cell10 = row.createCell(c);
        cell10.setCellValue(decisionForABusinessTrip.getDecisionNumber());
        cell10.setCellStyle(cellStyleCreator.getRightAquaBoldCellStyle(workbook));

        Row row2 = sheet.createRow(row_counter++);
        c=0;
        row.createCell(c++);
        Cell cell1 = row2.createCell(c);
        cell1.setCellValue("(Naziv firme)");
        cell1.setCellStyle(cellStyleCreator.getLeftTopSmallCellStyle(workbook));
        
        sheet.createRow(row_counter++);

        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 6));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 10));
    }

    private void createTable(DecisionForABusinessTrip decisionForABusinessTrip, PropertyTemplate propertyTemplate, XSSFWorkbook workbook, XSSFSheet sheet) {
        int number = 0;
        createOneRowInTable(++number +".","Ime i prezime zaposlenog", decisionForABusinessTrip.getEmployeeFullName(), sheet, workbook);
        createOneRowInTable(++number +".","Radno mesto", decisionForABusinessTrip.getEmployeeDesignationName(), sheet, workbook);
        createOneRowInTable(++number +".","Cilj putovanja", decisionForABusinessTrip.getTravelPurpose(), sheet, workbook);

        List<DecisionTripInfo> decisionTripInfoList = new ArrayList<>(decisionForABusinessTrip.getDecisionTripInfo());
        decisionTripInfoList.sort(Comparator.comparing(p -> TimeAndDateParser.parseDate2(p.getDepartureDate())));

        flights_number=-1;
        for (DecisionTripInfo decisionTripInfo : decisionTripInfoList) {

            flights_number++;
            createOneRowInTable(++number + ".", "Naziv države i mesto u koje se putuje", decisionTripInfo.getArrivalCountry() + ", "
                    + decisionTripInfo.getArrivalCity(), sheet, workbook);
            createOneRowInTable(++number + ".", "Dan polaska na putovanje", decisionTripInfo.getDepartureDate(), sheet, workbook);
        }

        createOneRowInTable(++number +".","Trajanje putovanja u danima (planirano)", decisionForABusinessTrip.getTravelDuration(), sheet, workbook);
        createOneRowInTable(++number +".","Prevozna sredstva koja se mogu koristiti", decisionForABusinessTrip.getWaysOfTransport(), sheet, workbook);
        createOneRowInTable(++number +".","Besplatan smeštaj i hrana na putu", decisionForABusinessTrip.getFreeAccommodationAndFoodOnATrip(), sheet, workbook);

        createOneRowInTableAdvancedPayment(++number +".","Zaposlenom pripada akontacija u iznosu", decisionForABusinessTrip.getAdvancePaymentEUR(), decisionForABusinessTrip.getAdvancePaymentRSD(), sheet, workbook);
        createOneRowInTableAllowance(++number +".","Visina dnevnice u inostranstvu","EUR", decisionForABusinessTrip.getDailyAllowanceEUR(), sheet, workbook);
        createOneRowInTableAllowance(++number +".","Visina dnevnice u zemlji","dinara", decisionForABusinessTrip.getDailyAllowanceRSD(), sheet, workbook);


        mergeRegions(sheet);
        drawBorders(propertyTemplate);
        drawBordersAdvancedPayment(propertyTemplate);
        mergeRegionsAdvancedPayment(sheet);
        mergeRegionsAllowance(sheet);

    }

    private void createTableForSignature(LocalDate decisionDate, XSSFWorkbook workbook, XSSFSheet sheet) {

        sheet.createRow(row_counter++);
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);

        Cell cell = row.createCell(c++);
        cell.setCellValue("U Novom Sadu,");
        cell.setCellStyle(cellStyleCreator.getGreenBackgroundCellStyle(workbook));
        c=createMultipleCells(row, c,5);
        Cell cell2 = row.createCell(c++);
        cell2.setCellValue("                                                                                        ");
        cell2.setCellStyle(cellStyleCreator.getUnderlinedCellStyle(workbook));

        createMultipleCells(row, c,3);


        Row row2 = sheet.createRow(row_counter++);
        c=0;
        row2.createCell(c++);
        String formattedDate = timeAndDateParser.transformDateString(decisionDate);

        Cell cell1 = row2.createCell(c++);
        cell1.setCellValue(formattedDate);
        cell1.setCellStyle(cellStyleCreator.getRightWithGreenBackgroundCellStyle(workbook));
        row2.createCell(c++);
        Cell cell22 = row2.createCell(c++);
        cell22.setCellValue("godine");
        c=createMultipleCells(row2, c,3);

        Cell cell23 = row2.createCell(c++);
        cell23.setCellValue("Nalogodavac - ovlašćeno lice - potpis");
        cell23.setCellStyle(cellStyleCreator.getTopCenteredSmallCellStyle(workbook));

        createMultipleCells(row2, c,3);

        int i = (flights_number==0) ? 26 : 26+flights_number*2;

        sheet.addMergedRegion(new CellRangeAddress(i, i, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(i, i, 4, 6));
        sheet.addMergedRegion(new CellRangeAddress(i, i, 7, 10));

        sheet.addMergedRegion(new CellRangeAddress(i+1, i+1, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(i+1, i+1, 5, 6));
        sheet.addMergedRegion(new CellRangeAddress(i+1, i+1, 7, 10));
    }


    private void mergeRegionsAllowance(XSSFSheet sheet) {
        int i = (flights_number==0) ? 23 : 23+flights_number*2;

        sheet.addMergedRegion(new CellRangeAddress(i, i, 2, 6));
        sheet.addMergedRegion(new CellRangeAddress(i, i, 8, 10));

        sheet.addMergedRegion(new CellRangeAddress(i+1, i+1, 2, 6));
        sheet.addMergedRegion(new CellRangeAddress(i+1, i+1, 8, 10));
    }

    private void mergeRegionsAdvancedPayment(XSSFSheet sheet) {
        int i = (flights_number==0) ? 22 : 22+flights_number*2;

        sheet.addMergedRegion(new CellRangeAddress(i, i, 2, 5));
        sheet.addMergedRegion(new CellRangeAddress(i, i, 8, 9));

    }

    private void drawBordersAdvancedPayment(PropertyTemplate propertyTemplate) {
        int i = (flights_number==0) ? 22 : 22+flights_number*2;
        propertyTemplate.drawBorders(new CellRangeAddress(i, i, 1, 1), BorderStyle.DOTTED, BorderExtent.OUTSIDE);
        propertyTemplate.drawBorders(new CellRangeAddress(i, i, 7, 10), BorderStyle.DOTTED, BorderExtent.OUTSIDE);

        propertyTemplate.drawBorders(new CellRangeAddress(i, i, 2, 5), BorderStyle.DOTTED, BorderExtent.OUTSIDE);
        propertyTemplate.drawBorders(new CellRangeAddress(i, i, 6, 6), BorderStyle.DOTTED, BorderExtent.OUTSIDE);
    }

    private void drawBorders(PropertyTemplate propertyTemplate) {

        int i =14;
        int j =(flights_number==0) ? 25 : 25+flights_number*2;
        while(i<j) {
            propertyTemplate.drawBorders(new CellRangeAddress(i, i, 1, 1), BorderStyle.DOTTED, BorderExtent.OUTSIDE);
            propertyTemplate.drawBorders(new CellRangeAddress(i, i, 7, 10), BorderStyle.DOTTED, BorderExtent.OUTSIDE);
            propertyTemplate.drawBorders(new CellRangeAddress(i, i, 2, 6), BorderStyle.DOTTED, BorderExtent.OUTSIDE);
            i++;
        }
        propertyTemplate.drawBorders(new CellRangeAddress(14, j-1, 1, 10), BorderStyle.THIN, BorderExtent.OUTSIDE);

    }

    private void mergeRegions(XSSFSheet sheet) {
        int i = 14;
        int j =(flights_number==0) ? 22 : 22+flights_number*2;
        while(i<j) {

            sheet.addMergedRegion(new CellRangeAddress(i, i, 2, 6));
            sheet.addMergedRegion(new CellRangeAddress(i,i,7,10));
            i++;

        }

    }

    private void createOneRowInTableAllowance(String number, String title, String currency, Double amount, XSSFSheet sheet, XSSFWorkbook workbook) {

        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);

        Cell cell0 = row.createCell(c++);
        cell0.setCellValue(number);
        cell0.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));

        Cell cell = row.createCell(c++);
        cell.setCellValue(title);
        c = createMultipleCells(row, c,4);

        Cell cell6 = row.createCell(c++);
        cell6.setCellValue(currency);
        cell6.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.CENTER));


        Cell cell8 = row.createCell(c++);
        cell8.setCellValue(amount);
        cell8.setCellStyle(cellStyleCreator.getFormattedRightWithGreenBackgroundCellStyle(workbook));
        createMultipleCells(row, c,2);

    }

    private void createOneRowInTableAdvancedPayment(String number,String title, Double advancePaymentEUR, Double advancePaymentRSD, XSSFSheet sheet, XSSFWorkbook workbook) {

        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);

        Cell cell0 = row.createCell(c++);
        cell0.setCellValue(number);
        cell0.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));

        Cell cell = row.createCell(c++);
        cell.setCellValue(title);
        c = createMultipleCells(row, c,3);

        Cell cell5 = row.createCell(c++);
        cell5.setCellValue("EUR");
        cell5.setCellStyle(cellStyleCreator.getRightWithGreenBackgroundCellStyle(workbook));

        Cell cell6 = row.createCell(c++);
        cell6.setCellValue(String.valueOf(advancePaymentEUR));
        cell6.setCellStyle(cellStyleCreator.getFormattedRightGreenBackgroundCellStyle(workbook));
        Cell cell7= row.createCell(c++);
        cell7.setCellValue("i dinara");
        cell7.setCellStyle(cellStyleCreator.getRightWithGreenBackgroundCellStyle(workbook));
        row.createCell(c++);

        Cell cell8 = row.createCell(c);
        cell8.setCellValue(String.valueOf(advancePaymentRSD));
        cell8.setCellStyle(cellStyleCreator.getFormattedRightGreenBackgroundCellStyle(workbook));

    }

    private void createOneRowInTable(String number, String title, String content, XSSFSheet sheet, XSSFWorkbook workbook) {

        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);
        Cell cell0 = row.createCell(c++);
        cell0.setCellValue(number);
        cell0.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));

        Cell cell = row.createCell(c++);
        cell.setCellValue(title);
        c = createMultipleCells(row, c,4);
        Cell cell6 = row.createCell(c++);
        cell6.setCellValue(content);
        cell6.setCellStyle(cellStyleCreator.getRightWithGreenBackgroundCellStyle(workbook));

        row.createCell(c++);
        row.createCell(c);
    }

    private int createMultipleCells(Row row, int c, int number) {
        int i =0;
        while(i<number) {
            row.createCell(c++);
            i++;
        }
        return c;
    }

}
