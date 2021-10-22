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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class OrderForABusinessTripCreator {

    private static int row_counter = 0;
    private static int flights_number = 0;

    @Autowired
    CellStyleCreator cellStyleCreator;

    @Autowired
    TimeAndDateParser timeAndDateParser;


    public void createContentForOrder(XSSFSheet orderSheet, XSSFWorkbook workbook, DecisionForABusinessTrip decisionForABusinessTrip) {
        row_counter =0;
        flights_number=0;
        PropertyTemplate propertyTemplate = new PropertyTemplate();

        createSynechronTitleInOrder(workbook, orderSheet);
        createDocumentPurposeOrder(workbook, orderSheet,decisionForABusinessTrip);

        mergeBasicInformationTable(orderSheet);
        createTable(decisionForABusinessTrip, propertyTemplate, workbook, orderSheet);
        createTableForSignature(decisionForABusinessTrip.getDecisionDate(), workbook, orderSheet);

        propertyTemplate.applyBorders(orderSheet);

    }

    private void mergeBasicInformationTable(XSSFSheet sheet) {

        sheet.addMergedRegion(new CellRangeAddress(4, 4, 1, 10));
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 10));
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 10));

    }

    private void createSynechronTitleInOrder( XSSFWorkbook workbook, XSSFSheet sheet) {
        sheet.createRow(row_counter++);
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);

        Cell cell = row.createCell(c++);
        cell.setCellValue("SYNECHRON SRB doo, Novi Sad");
        cell.setCellStyle(cellStyleCreator.getLeftBoldUnderlinedCellStyle(workbook));

        c = createMultipleCells(row, c,9);

        Row row2 = sheet.createRow(row_counter++);
        c=0;
        row.createCell(c++);
        Cell cell1 = row2.createCell(c);
        cell1.setCellValue("(Naziv firme)");
        cell1.setCellStyle(cellStyleCreator.getLeftTopSmallCellStyle(workbook));

        sheet.createRow(row_counter++);

        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 10));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 10));
    }
    private void createDocumentPurposeOrder(XSSFWorkbook workbook, XSSFSheet sheet,DecisionForABusinessTrip decisionForABusinessTrip) {

        getOneRowOfPurpose(workbook, sheet, "NALOG");
        getOneRowOfPurpose(workbook, sheet, "ZA SLUŽBENI PUT U INOSTRANSTVO");
        getOneRowOfPurpose(workbook, sheet, "BROJ " +decisionForABusinessTrip.getDecisionNumber());

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
        cell6.setCellStyle(cellStyleCreator.getRightItalic(workbook));

        row.createCell(c++);
        row.createCell(c);
    }

    private void createTableForSignature(LocalDate decisionDate, XSSFWorkbook workbook, XSSFSheet sheet) {

        sheet.createRow(row_counter++);
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);

        Cell cell = row.createCell(c++);
        cell.setCellValue("U Novom Sadu,");
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
        cell1.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook,HorizontalAlignment.RIGHT));
        row2.createCell(c++);
        Cell cell22 = row2.createCell(c++);
        cell22.setCellValue("godine");
        c=createMultipleCells(row2, c,3);

        Cell cell23 = row2.createCell(c++);
        cell23.setCellValue("Nalogodavac - ovlašćeno lice - potpis");
        cell23.setCellStyle(cellStyleCreator.getTopCenteredSmallCellStyle(workbook));

        createMultipleCells(row2, c,3);

        int i = (flights_number==0) ? 20 : 20+flights_number*2;

        sheet.addMergedRegion(new CellRangeAddress(i, i, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(i, i, 4, 6));
        sheet.addMergedRegion(new CellRangeAddress(i, i, 7, 10));

        sheet.addMergedRegion(new CellRangeAddress(i+1, i+1, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(i+1, i+1, 5, 6));
        sheet.addMergedRegion(new CellRangeAddress(i+1, i+1, 7, 10));
    }

    private void drawBordersAdvancedPayment(PropertyTemplate propertyTemplate) {
        int i = (flights_number==0) ? 16 : 16+flights_number*2;
        propertyTemplate.drawBorders(new CellRangeAddress(i, i, 1, 1), BorderStyle.DOTTED, BorderExtent.OUTSIDE);
        propertyTemplate.drawBorders(new CellRangeAddress(i, i, 7, 10), BorderStyle.DOTTED, BorderExtent.OUTSIDE);

        propertyTemplate.drawBorders(new CellRangeAddress(i, i, 2, 5), BorderStyle.DOTTED, BorderExtent.OUTSIDE);
        propertyTemplate.drawBorders(new CellRangeAddress(i, i, 6, 6), BorderStyle.DOTTED, BorderExtent.OUTSIDE);
    }

    private void drawBorders(PropertyTemplate propertyTemplate) {

        int i =8;
        int j =(flights_number==0) ? 19 : 19+flights_number*2;
        while(i<j) {
            propertyTemplate.drawBorders(new CellRangeAddress(i, i, 1, 1), BorderStyle.DOTTED, BorderExtent.OUTSIDE);
            propertyTemplate.drawBorders(new CellRangeAddress(i, i, 7, 10), BorderStyle.DOTTED, BorderExtent.OUTSIDE);
            propertyTemplate.drawBorders(new CellRangeAddress(i, i, 2, 6), BorderStyle.DOTTED, BorderExtent.OUTSIDE);
            i++;
        }
        propertyTemplate.drawBorders(new CellRangeAddress(8, j-1, 1, 10), BorderStyle.THIN, BorderExtent.OUTSIDE);

    }

    private void mergeRegions(XSSFSheet sheet) {
        int i = 8;
        int j =(flights_number==0) ? 16 : 16+flights_number*2;
        while(i<j) {

            sheet.addMergedRegion(new CellRangeAddress(i, i, 2, 6));
            sheet.addMergedRegion(new CellRangeAddress(i,i,7,10));
            i++;

        }

    }
    private void mergeRegionsAllowance(XSSFSheet sheet) {
        int i = (flights_number==0) ? 17 : 17+flights_number*2;

        sheet.addMergedRegion(new CellRangeAddress(i, i, 2, 6));
        sheet.addMergedRegion(new CellRangeAddress(i, i, 8, 10));

        sheet.addMergedRegion(new CellRangeAddress(i+1, i+1, 2, 6));
        sheet.addMergedRegion(new CellRangeAddress(i+1, i+1, 8, 10));
    }

    private void mergeRegionsAdvancedPayment(XSSFSheet sheet) {
        int i = (flights_number==0) ? 16 : 16+flights_number*2;

        sheet.addMergedRegion(new CellRangeAddress(i, i, 2, 5));
        sheet.addMergedRegion(new CellRangeAddress(i, i, 8, 9));

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
        cell8.setCellStyle(cellStyleCreator.getFormattedRightItalic(workbook));
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
        cell5.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));

        Cell cell6 = row.createCell(c++);
        cell6.setCellValue(String.valueOf(advancePaymentEUR));
        cell6.setCellStyle(cellStyleCreator.getFormattedRightItalic(workbook));
        Cell cell7= row.createCell(c++);
        cell7.setCellValue("i dinara");
        cell7.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));
        row.createCell(c++);

        Cell cell8 = row.createCell(c);
        cell8.setCellValue(String.valueOf(advancePaymentRSD));
        cell8.setCellStyle(cellStyleCreator.getFormattedRightItalic(workbook));

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
