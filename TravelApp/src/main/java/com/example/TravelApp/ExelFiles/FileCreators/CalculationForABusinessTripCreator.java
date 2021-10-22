package com.example.TravelApp.ExelFiles.FileCreators;

import com.example.TravelApp.DTOs.CalculationDTO;
import com.example.TravelApp.DTOs.CurrencyAmountDTO;
import com.example.TravelApp.Helpers.HoursAndMoneyCounter;
import com.example.TravelApp.Helpers.TimeAndDateParser;
import com.example.TravelApp.Model.DecisionForABusinessTrip;
import com.example.TravelApp.Model.DecisionTripInfo;
import com.example.TravelApp.Model.TravelDetails;
import com.example.TravelApp.Service.Implementations.NBSServiceImpl;
import com.example.TravelApp.Service.Interfaces.ITravelDetailsService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PropertyTemplate;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CalculationForABusinessTripCreator {

    @Autowired
    ITravelDetailsService travelDetailsService;

    @Autowired
    TimeAndDateParser timeAndDateParser;

    @Autowired
    HoursAndMoneyCounter hoursAndMoneyCounter;

    @Autowired
    CellStyleCreator cellStyleCreator;

    @Autowired
    NBSServiceImpl nbsService;

    private static int row_counter = 0;
    private static int flights_number = 0;

    private CalculationDTO calculationDTO= new CalculationDTO();

    public void createCalculation(DecisionForABusinessTrip decisionForABusinessTrip, String filePath) {
        File file = new File(filePath);
        FileInputStream fileInputStream = null;
        XSSFWorkbook workbook = null;
        try {
            fileInputStream = new FileInputStream(file);
            workbook =new XSSFWorkbook(fileInputStream);

        } catch (IOException e ) {
            e.printStackTrace();
        }
        row_counter = 0;
        flights_number = 0;
        createCalculationContent(decisionForABusinessTrip,workbook);

        try {
            fileInputStream.close();
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void createCalculationContent(DecisionForABusinessTrip decisionForABusinessTrip,XSSFWorkbook workbook) {
        XSSFSheet calculationSheet = workbook.getSheet("Obračun SPI");
        if(calculationSheet==null) {
            calculationSheet = workbook.createSheet("Obračun SPI");
        }else {
            return;
        }

        PropertyTemplate propertyTemplate = new PropertyTemplate();
        createBasicInformationTable(decisionForABusinessTrip, propertyTemplate, workbook, calculationSheet);

        createTable(decisionForABusinessTrip, propertyTemplate, workbook, calculationSheet);
    }

    private void createTable(DecisionForABusinessTrip decisionForABusinessTrip, PropertyTemplate propertyTemplate, XSSFWorkbook workbook, XSSFSheet calculationSheet) {
        createBasinInfoInTable(decisionForABusinessTrip, workbook,  calculationSheet);

        createRow2_0(workbook,  calculationSheet);

        createAllowancesInTable(decisionForABusinessTrip, workbook,  calculationSheet);

        createCostsInTable(decisionForABusinessTrip, workbook,  calculationSheet);

        createOtherCostsInTable(decisionForABusinessTrip, workbook,  calculationSheet);
        createAllCostsInTable(decisionForABusinessTrip, workbook,  calculationSheet);
        createPayedCostsInTable(decisionForABusinessTrip, workbook,  calculationSheet);

        createSigranturePart(workbook,calculationSheet);
        mergeRegions(calculationSheet,8,17);
        mergeRegionsSecondPart(calculationSheet,17,22);
        mergeRegionsCosts(calculationSheet,23,26);
        mergeRegionsSecondPart(calculationSheet,26,28);

        mergeRegionsCosts(calculationSheet,29,32);
        mergeRegionsSecondPart(calculationSheet,32,34);

        mergeRegionsSum(calculationSheet);

        mergeRegionsCosts(calculationSheet,35,42);
        mergeRegionsSecondPart(calculationSheet,42,44);

        mergeRegionsSecondPart(calculationSheet,45,47);

        mergeRegionsSecondPart(calculationSheet,48,60);

        drawBorders(propertyTemplate,8,58);

        margeAndBordersSignaturePart(propertyTemplate,calculationSheet);
        propertyTemplate.applyBorders(calculationSheet);

    }

    private void margeAndBordersSignaturePart(PropertyTemplate propertyTemplate, XSSFSheet sheet) {
        sheet.addMergedRegion(new CellRangeAddress(60, 60, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(61, 64, 1, 13));

        propertyTemplate.drawBorders(new CellRangeAddress(61, 64, 1, 13), BorderStyle.THIN, BorderExtent.OUTSIDE);

        sheet.addMergedRegion(new CellRangeAddress(65, 65, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(65, 65, 3, 4));
        sheet.addMergedRegion(new CellRangeAddress(65, 65, 8, 10));
        sheet.addMergedRegion(new CellRangeAddress(65, 65, 11, 13));

        sheet.addMergedRegion(new CellRangeAddress(67, 67, 2, 6));

        //sheet.addMergedRegion(new CellRangeAddress(67, 67, 9, 13));

        sheet.addMergedRegion(new CellRangeAddress(68, 68, 2, 6));

        sheet.addMergedRegion(new CellRangeAddress(68, 68, 9, 13));
    }

    private void createSigranturePart(XSSFWorkbook workbook, XSSFSheet sheet) {
        row_counter++;
        row_counter++;
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);
        Cell cell0 = row.createCell(c++);
        cell0.setCellValue("Napomena:");
        row.createCell(c);
        c=0;
        Row row2 = sheet.createRow(row_counter++);
        row2.createCell(c++);
        Cell cell1 = row2.createCell(c++);
        cell1.setCellStyle(cellStyleCreator.getRightWithGreenBackgroundCellStyle(workbook));
        createMultipleCells(row2, c,13);
        c=0;
        Row row3 = sheet.createRow(row_counter++);
        row3.createCell(c++);
        createMultipleCells(row3, c,14);

        c=0;
        Row row4 = sheet.createRow(row_counter++);
        row4.createCell(c++);
        createMultipleCells(row4, c,14);

        c=0;
        Row row5 = sheet.createRow(row_counter++);
        row5.createCell(c++);
        createMultipleCells(row5, c,14);


        Row row6 = sheet.createRow(row_counter++);
        c = 0;
        row6.createCell(c++);

        Cell cell = row6.createCell(c++);
        cell.setCellValue("U Novom Sadu,");
        row6.createCell(c++);

        String formattedDate = timeAndDateParser.transformDateString(LocalDate.now());
        Cell cell2 = row6.createCell(c++);
        cell2.setCellValue(formattedDate);
        cell2.setCellStyle(cellStyleCreator.getRightWithGreenBackgroundCellStyle(workbook));
        row6.createCell(c++);

        Cell cell3 = row6.createCell(c++);
        cell3.setCellValue("godine");
        row6.createCell(c++);
        row6.createCell(c++);

        Cell cell4 = row6.createCell(c++);
        cell4.setCellValue("Broj priloga uz obračun:");
        cell4.setCellStyle(cellStyleCreator.getRightAquaBoldCellStyle(workbook));
        c= createMultipleCells(row6, c,2);

        Cell cell5 = row6.createCell(c++);

        cell5.setCellStyle(cellStyleCreator.getLeftGreenBackgroundCellStyle(workbook));
        cell5.setCellValue("1");
        row6.createCell(c++);
        row6.createCell(c);

        row_counter++;
        Row row7 = sheet.createRow(row_counter++);
        c=0;
        row7.createCell(c++);
        row7.createCell(c++);

        Cell cell6 = row7.createCell(c++);
        cell6.setCellValue("                                                                                             ");
        cell6.setCellStyle(cellStyleCreator.getUnderlinedCellStyle(workbook));
        c= createMultipleCells(row7, c,6);

        Cell cell7 = row7.createCell(c++);

        cell7.setCellValue("                                                                                             ");
        cell7.setCellStyle(cellStyleCreator.getUnderlinedCellStyle(workbook));
        createMultipleCells(row7, c,4);

        Row row8 = sheet.createRow(row_counter++);
        c=0;
        row8.createCell(c++);
        row8.createCell(c++);

        Cell cell8 = row8.createCell(c++);
        cell8.setCellValue("(Potpis podnosioca obračuna)");
        cell8.setCellStyle(cellStyleCreator.getCenteredCellStyle(workbook));
        c=createMultipleCells(row8, c,4);

        row8.createCell(c++);
        row8.createCell(c++);

        Cell cell9 = row8.createCell(c++);
        cell9.setCellValue("(Potpis ovlašćenog lica nalogodavca)");
        cell9.setCellStyle(cellStyleCreator.getCenteredCellStyle(workbook));
        createMultipleCells(row8, c,4);

    }

    private void createPayedCostsInTable(DecisionForABusinessTrip decisionForABusinessTrip, XSSFWorkbook workbook, XSSFSheet sheet) {
        int number =0;
        CurrencyAmountDTO currencyAmountEUR = new CurrencyAmountDTO("EUR",0.00,0.00,"","");
        CurrencyAmountDTO advancedPaymentRSD = new CurrencyAmountDTO("RSD",0.00,0.00,"","");
        CurrencyAmountDTO currencyAmountEURsum = new CurrencyAmountDTO("EUR",0.00,0.00,"K47-K50-K52-K54-K56","K58*M3");
        CurrencyAmountDTO advancedPaymentRSDsum = new CurrencyAmountDTO("RSD",0.00,0.00,"K46-K49-K51-K53-K55","K57");

        advancedPaymentRSD.setFormulaDinProtivr("K49");
        createOneRowInTableAllowance("7." + ++number,"Primljena akontacija u dinarima",advancedPaymentRSD, sheet, workbook);
        currencyAmountEUR.setFormulaDinProtivr("K50*M3");
        createOneRowInTableAllowance("7." + ++number,"Primljena akontacija u devizama", currencyAmountEUR, sheet, workbook);
        advancedPaymentRSD.setFormulaDinProtivr("K51");
        createOneRowInTableAllowance("7." + ++number,"Plaćeni troškovi putem biznis kartice u dinarima", advancedPaymentRSD, sheet, workbook);
        currencyAmountEUR.setFormulaDinProtivr("K52*M3");
        createOneRowInTableAllowance("7." + ++number,"Plaćeni troškovi putem biznis kartice u devizama", currencyAmountEUR, sheet, workbook);
        advancedPaymentRSD.setFormulaDinProtivr("K53");

        createOneRowInTableAllowance("7." + ++number,"Plaćeni troškovi doznakama sa tekućeg računa", advancedPaymentRSD, sheet, workbook);
        currencyAmountEUR.setFormulaDinProtivr("K54*M3");

        createOneRowInTableAllowance("7." + ++number,"Plaćeni troškovi doznakama sa deviznog računa", currencyAmountEUR, sheet, workbook);

        advancedPaymentRSD.setFormulaDinProtivr("K55");
        createOneRowInTableCurrenciesGreen("7." + ++number,"Ostali iznosi u dinarima koji se odbijaju", advancedPaymentRSD, sheet, workbook);
        currencyAmountEUR.setFormulaDinProtivr("K56*M3");
        createOneRowInTableCurrenciesGreen("7." + ++number,"Ostali iznosi u devizama koji se odbijaju", currencyAmountEUR, sheet, workbook);
        createOneRowInTableSumBolded("7." + ++number,"Za isplatu u dinarima (6.1.-7.1.-7.3.-7.5.-7.7.)", advancedPaymentRSDsum, sheet, workbook);
        createOneRowInTableSumBolded("7." + ++number,"Za isplatu u devizama (6.2.-7.2.-7.4.-7.6.-7.8.)", currencyAmountEURsum, sheet, workbook);

    }

    private void createOneRowInTableSumBolded(String number, String title, CurrencyAmountDTO currencyAmountDTO, XSSFSheet sheet, XSSFWorkbook workbook) {
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);
        Cell cell0 = row.createCell(c++);
        cell0.setCellValue(number);
        cell0.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));

        Cell cell = row.createCell(c++);
        cell.setCellValue(title);
        cell.setCellStyle(cellStyleCreator.getBoldedCellStyle(workbook));
        c = createMultipleCells(row, c,5);


        Cell cell1 = row.createCell(c++);
        cell1.setCellValue(currencyAmountDTO.getCurrency());
        cell1.setCellStyle(cellStyleCreator.getCenteredBoldCellStyle(workbook));
        row.createCell(c++);

        Cell cell2 = row.createCell(c++);
        cell2.setCellFormula(currencyAmountDTO.getFormula());
        cell2.setCellStyle(cellStyleCreator.getCenteredBoldFormattedNormalCellStyle(workbook));
        row.createCell(c++);

        Cell cell3 = row.createCell(c++);
        cell3.setCellFormula(currencyAmountDTO.getFormulaDinProtivr());
        cell3.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));
        row.createCell(c);


    }

    private void createAllCostsInTable(DecisionForABusinessTrip decisionForABusinessTrip, XSSFWorkbook workbook, XSSFSheet sheet) {
        int number =0;
        CurrencyAmountDTO currencyAmountRSD = new CurrencyAmountDTO("RSD",0.00,0.00,"SUM(K21,K27,K33,K43)","K46");
        CurrencyAmountDTO currencyAmountEUR = new CurrencyAmountDTO("EUR",0.00,0.00,"SUM(K22,K28,K34,K44)","K47*M3");

        createOneRowInTableCurrencies("6." + ++number,"Ukupan obračun u dinarima (2.3. + 3.4. + 4.4. + 5.8.)", currencyAmountRSD, sheet, workbook);
        createOneRowInTableCurrencies("6." + ++number,"Ukupan obračun u devizama (2.4. + 3.5. + 4.5. + 5.9.)", currencyAmountEUR, sheet, workbook);
        createOneRowInTableSum("6." + ++number,"Sveobuhvatan obračun po nalogu (5.8. + 5.9.)", "SUM(M46,M47)", sheet, workbook);

    }

    private void createOtherCostsInTable(DecisionForABusinessTrip decisionForABusinessTrip, XSSFWorkbook workbook, XSSFSheet sheet) {
        int number=0;
        CurrencyAmountDTO currencyAmountDTO = new CurrencyAmountDTO("EUR",0.00,0.00,"","");
        currencyAmountDTO.setFormulaDinProtivr("K36*M3");
        createOneRowInTableCostsGreen("5." + ++number,"Ostali troškovi","",currencyAmountDTO, sheet, workbook);
        currencyAmountDTO.setFormulaDinProtivr("K37*M3");
        createOneRowInTableCostsGreen("5." + ++number,"Ostali troškovi","",currencyAmountDTO, sheet, workbook);
        currencyAmountDTO.setFormulaDinProtivr("K38*M3");
        createOneRowInTableCostsGreen("5." + ++number,"Ostali troškovi","",currencyAmountDTO, sheet, workbook);
        currencyAmountDTO.setFormulaDinProtivr("K39*M3");
        createOneRowInTableCostsGreen("5." + ++number,"Ostali troškovi","",currencyAmountDTO, sheet, workbook);
        currencyAmountDTO.setFormulaDinProtivr("K40*M3");
        createOneRowInTableCostsGreen("5." + ++number,"Ostali troškovi","",currencyAmountDTO, sheet, workbook);
        currencyAmountDTO.setFormulaDinProtivr("K41*M3");
        createOneRowInTableCostsGreen("5." + ++number,"Ostali troškovi","",currencyAmountDTO, sheet, workbook);
        currencyAmountDTO.setFormulaDinProtivr("K42*M3");
        createOneRowInTableCostsGreen("5." + ++number,"Ostali troškovi","",currencyAmountDTO, sheet, workbook);
        CurrencyAmountDTO currencyAmountRSD = new CurrencyAmountDTO("RSD",0.00,0.00,"","K43");
        CurrencyAmountDTO currencyAmountEUR = new CurrencyAmountDTO("EUR",0.00,0.00,"SUM(K36,K37,K38,K39,K40,K41,K42)","K44*M3");

        createOneRowInTableCurrenciesGreenRSD("5." + ++number,"Ostali troškovi u dinarima (Σ 5.1. do 5.7. RSD)", currencyAmountRSD, sheet, workbook);
        createOneRowInTableCurrenciesGreenWithFormula("5." + ++number,"Ostali troškovi u devizama (Σ 5.1. do 5.7. devize)", currencyAmountEUR, sheet, workbook);
        createOneRowInTableSumGreen("5." + ++number,"Ukupno ostali troškovi (5.8. + 5.9.)", "SUM(M43,M44)", sheet, workbook);
    }

    private void createOneRowInTableCurrenciesGreenRSD(String number, String title, CurrencyAmountDTO currencyAmountDTO, XSSFSheet sheet, XSSFWorkbook workbook) {
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);
        Cell cell0 = row.createCell(c++);
        cell0.setCellValue(number);
        cell0.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));

        Cell cell = row.createCell(c++);
        cell.setCellValue(title);
        cell.setCellStyle(cellStyleCreator.getLeftGreenBoldCellStyle(workbook));
        c = createMultipleCells(row, c,5);


        Cell cell1 = row.createCell(c++);
        cell1.setCellValue(currencyAmountDTO.getCurrency());
        cell1.setCellStyle(cellStyleCreator.getCenteredCellStyle(workbook));
        row.createCell(c++);

        Cell cell2 = row.createCell(c++);
        cell2.setCellValue(currencyAmountDTO.getAmount());
        cell2.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));
        row.createCell(c++);

        Cell cell3 = row.createCell(c++);
        cell3.setCellFormula(currencyAmountDTO.getFormulaDinProtivr());
        cell3.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));
        row.createCell(c);

    }

    private void createOneRowInTableCurrenciesGreenWithFormula(String number, String title, CurrencyAmountDTO currencyAmountDTO, XSSFSheet sheet, XSSFWorkbook workbook) {
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);
        Cell cell0 = row.createCell(c++);
        cell0.setCellValue(number);
        cell0.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));

        Cell cell = row.createCell(c++);
        cell.setCellValue(title);
        cell.setCellStyle(cellStyleCreator.getLeftGreenBoldCellStyle(workbook));
        c = createMultipleCells(row, c,5);


        Cell cell1 = row.createCell(c++);
        cell1.setCellValue(currencyAmountDTO.getCurrency());
        cell1.setCellStyle(cellStyleCreator.getCenteredCellStyle(workbook));
        row.createCell(c++);

        Cell cell2 = row.createCell(c++);
        cell2.setCellFormula(currencyAmountDTO.getFormula());
        cell2.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));
        row.createCell(c++);

        Cell cell3 = row.createCell(c++);
        cell3.setCellFormula(currencyAmountDTO.getFormulaDinProtivr());
        cell3.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));
        row.createCell(c);
    }

    private void createOneRowInTableSumGreen(String number, String title, String formula, XSSFSheet sheet, XSSFWorkbook workbook) {
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);
        Cell cell0 = row.createCell(c++);
        cell0.setCellValue(number);
        cell0.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));

        Cell cell = row.createCell(c++);
        cell.setCellValue(title);
        cell.setCellStyle(cellStyleCreator.getLeftGreenBoldCellStyle(workbook));
        c = createMultipleCells(row, c,5);


        Cell cell1 = row.createCell(c++);
        cell1.setCellFormula(formula);
        cell1.setCellStyle(cellStyleCreator.getRightBoldedFormattedCellStyle(workbook));
        createMultipleCells(row, c,5);


    }

    private void createOneRowInTableCurrenciesGreen(String number, String title, CurrencyAmountDTO currencyAmountDTO, XSSFSheet sheet, XSSFWorkbook workbook) {

        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);
        Cell cell0 = row.createCell(c++);
        cell0.setCellValue(number);
        cell0.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));

        Cell cell = row.createCell(c++);
        cell.setCellValue(title);
        cell.setCellStyle(cellStyleCreator.getLeftGreenBoldCellStyle(workbook));

        c = createMultipleCells(row, c,5);
        Cell cell1 = row.createCell(c++);
        cell1.setCellValue(currencyAmountDTO.getCurrency());
        cell1.setCellStyle(cellStyleCreator.getCenteredCellStyle(workbook));
        row.createCell(c++);

        Cell cell2 = row.createCell(c++);
        cell2.setCellValue(currencyAmountDTO.getAmount());
        cell2.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));
        row.createCell(c++);

        Cell cell3 = row.createCell(c++);
        cell3.setCellFormula(currencyAmountDTO.getFormulaDinProtivr());
        cell3.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));
        row.createCell(c);
    }

    private void createOneRowInTableCostsGreen(String number, String title, String s1, CurrencyAmountDTO currencyAmountDTO, XSSFSheet sheet, XSSFWorkbook workbook) {
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);
        Cell cell0 = row.createCell(c++);
        cell0.setCellValue(number);
        cell0.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));

        Cell cell = row.createCell(c++);
        cell.setCellValue(title);
        cell.setCellStyle(cellStyleCreator.getAquaCellStyle(workbook));
        c = createMultipleCells(row, c,2);

        Cell cell11 = row.createCell(c++);
        cell11.setCellValue("");
        cell11.setCellStyle(cellStyleCreator.getLightGreenBackgroundCellStyle(workbook));

        c = createMultipleCells(row, c,2);


        Cell cell1 = row.createCell(c++);
        cell1.setCellValue(currencyAmountDTO.getCurrency());
        cell1.setCellStyle(cellStyleCreator.getCenteredCellStyle(workbook));
        row.createCell(c++);

        Cell cell2 = row.createCell(c++);
        cell2.setCellValue(currencyAmountDTO.getAmount());
        cell2.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));
        row.createCell(c++);

        Cell cell3 = row.createCell(c++);
        cell3.setCellFormula(currencyAmountDTO.getFormulaDinProtivr());
        cell3.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));
        row.createCell(c);


    }

    private void mergeRegionsSum(XSSFSheet sheet) {
        sheet.addMergedRegion(new CellRangeAddress(22, 22, 8, 13));
        sheet.addMergedRegion(new CellRangeAddress(28, 28, 8, 13));
        sheet.addMergedRegion(new CellRangeAddress(34, 34, 8, 13));
        sheet.addMergedRegion(new CellRangeAddress(44, 44, 8, 13));
        sheet.addMergedRegion(new CellRangeAddress(44, 44, 2, 7));
        sheet.addMergedRegion(new CellRangeAddress(47, 47, 2, 7));
        sheet.addMergedRegion(new CellRangeAddress(47, 47, 8, 13));

    }

    private void mergeRegionsCosts(XSSFSheet sheet, int i, int j) {
        while(i<j) {
            sheet.addMergedRegion(new CellRangeAddress(i, i, 2, 4));
            sheet.addMergedRegion(new CellRangeAddress(i, i, 5, 7));
            sheet.addMergedRegion(new CellRangeAddress(i, i, 8, 9));
            sheet.addMergedRegion(new CellRangeAddress(i, i, 10, 11));
            sheet.addMergedRegion(new CellRangeAddress(i, i, 12, 13));

            i++;

        }
    }

    private void createCostsInTable(DecisionForABusinessTrip decisionForABusinessTrip, XSSFWorkbook workbook, XSSFSheet sheet) {
        int number = 0;

        CurrencyAmountDTO currencyAmountEUR = new CurrencyAmountDTO("EUR",0.00,0.00,"","");
        CurrencyAmountDTO currencyAmountRSD = new CurrencyAmountDTO("RSD",0.00,0.00,"","");

        currencyAmountEUR.setFormulaDinProtivr("K24*M3");
        createOneRowInTableCosts("3." + ++number,"Troškovi smeštaja","Hotel",currencyAmountEUR, sheet, workbook);
        currencyAmountEUR.setFormulaDinProtivr("K25*M3");
        createOneRowInTableCosts("3." + ++number,"Troškovi smeštaja","",currencyAmountEUR, sheet, workbook);
        currencyAmountEUR.setFormulaDinProtivr("K26*M3");

        createOneRowInTableCosts("3." + ++number,"Troškovi smeštaja","",currencyAmountEUR, sheet, workbook);
        currencyAmountRSD.setFormula("");
        currencyAmountRSD.setFormulaDinProtivr("K27");

        createOneRowInTableCurrenciesRSD("3." + ++number,"Troškovi smeštaja u dinarima (Σ 3.1. do 3.3. RSD)", currencyAmountRSD, sheet, workbook);
        currencyAmountEUR.setFormula("SUM(K24,K25,K26)");
        currencyAmountEUR.setFormulaDinProtivr("K28*M3");

        createOneRowInTableCurrencies("3." + ++number,"Troškovi smeštaja u devizama (Σ 3.1. do 3.3. devize)", currencyAmountEUR, sheet, workbook);
        createOneRowInTableSum("3." + ++number,"Ukupno troškovi smeštaja (3.4. + 3.5.)", "SUM(M27,M28)", sheet, workbook);

        number = 0;
        currencyAmountEUR.setFormula("");
        currencyAmountEUR.setFormulaDinProtivr("K30*M3");
        createOneRowInTableCosts("4." + ++number,"Troškovi prevoza","Avion",currencyAmountEUR, sheet, workbook);
        currencyAmountEUR.setFormulaDinProtivr("K31*M3");
        createOneRowInTableCosts("4." + ++number,"Troškovi prevoza","Prevoz NS-aerodrom BGD-NS",currencyAmountEUR, sheet, workbook);
        currencyAmountEUR.setFormulaDinProtivr("K32*M3");
        createOneRowInTableCosts("4." + ++number,"Troškovi prevoza","",currencyAmountEUR, sheet, workbook);
        currencyAmountRSD.setFormula("");
        currencyAmountRSD.setFormulaDinProtivr("K33");
        createOneRowInTableCurrenciesRSD("4." + ++number,"Troškovi prevoza u dinarima (Σ 4.1. do 4.3. RSD)", currencyAmountRSD, sheet, workbook);

        currencyAmountEUR.setFormula("SUM(K30,K31,K32)");
        currencyAmountEUR.setFormulaDinProtivr("K34*M3");
        createOneRowInTableCurrencies("4." + ++number,"Troškovi prevoza u devizama (Σ 4.1. do 4.3. devize)", currencyAmountEUR, sheet, workbook);

        createOneRowInTableSum("4." + ++number,"Ukupno troškovi prevoza (4.4. + 4.5.)", "SUM(M33,M34)", sheet, workbook);


    }

    private void createOneRowInTableCurrenciesRSD(String number, String title, CurrencyAmountDTO currencyAmountDTO, XSSFSheet sheet, XSSFWorkbook workbook) {
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);
        Cell cell0 = row.createCell(c++);
        cell0.setCellValue(number);
        cell0.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));

        Cell cell = row.createCell(c++);
        cell.setCellValue(title);
        cell.setCellStyle(cellStyleCreator.getBoldedCellStyle(workbook));
        c = createMultipleCells(row, c,5);


        Cell cell1 = row.createCell(c++);
        cell1.setCellValue(currencyAmountDTO.getCurrency());
        cell1.setCellStyle(cellStyleCreator.getCenteredCellStyle(workbook));
        row.createCell(c++);

        Cell cell2 = row.createCell(c++);
        cell2.setCellValue(currencyAmountDTO.getAmount());
        cell2.setCellStyle(cellStyleCreator.getCenteredBoldFormattedNormalCellStyle(workbook));
        row.createCell(c++);

        Cell cell3 = row.createCell(c++);
        cell3.setCellFormula(currencyAmountDTO.getFormulaDinProtivr());
        cell3.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));
        row.createCell(c);

    }

    private void createOneRowInTableSum(String number, String title, String formula, XSSFSheet sheet, XSSFWorkbook workbook) {
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);
        Cell cell0 = row.createCell(c++);
        cell0.setCellValue(number);
        cell0.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));

        Cell cell = row.createCell(c++);
        cell.setCellValue(title);
        cell.setCellStyle(cellStyleCreator.getBoldedCellStyle(workbook));
        c = createMultipleCells(row, c,5);


        Cell cell1 = row.createCell(c++);
        cell1.setCellFormula(formula);
        cell1.setCellStyle(cellStyleCreator.getRightBoldedFormattedCellStyle(workbook));
        createMultipleCells(row, c,5);


    }

    private void createOneRowInTableCurrencies(String number, String title, CurrencyAmountDTO currencyAmountDTO, XSSFSheet sheet, XSSFWorkbook workbook) {
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);
        Cell cell0 = row.createCell(c++);
        cell0.setCellValue(number);
        cell0.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));

        Cell cell = row.createCell(c++);
        cell.setCellValue(title);
        cell.setCellStyle(cellStyleCreator.getBoldedCellStyle(workbook));
        c = createMultipleCells(row, c,5);


        Cell cell1 = row.createCell(c++);
        cell1.setCellValue(currencyAmountDTO.getCurrency());
        cell1.setCellStyle(cellStyleCreator.getCenteredCellStyle(workbook));
        row.createCell(c++);

        Cell cell2 = row.createCell(c++);
        cell2.setCellFormula(currencyAmountDTO.getFormula());
        cell2.setCellStyle(cellStyleCreator.getCenteredBoldFormattedNormalCellStyle(workbook));
        row.createCell(c++);

        Cell cell3 = row.createCell(c++);
        cell3.setCellFormula(currencyAmountDTO.getFormulaDinProtivr());
        cell3.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));
        row.createCell(c);

    }

    private void createOneRowInTableCosts(String number, String title, String extraInfo, CurrencyAmountDTO currencyAmountDTO, XSSFSheet sheet, XSSFWorkbook workbook) {
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);
        Cell cell0 = row.createCell(c++);
        cell0.setCellValue(number);
        cell0.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));

        Cell cell = row.createCell(c++);
        cell.setCellValue(title);
        c = createMultipleCells(row, c,2);

        Cell cell11 = row.createCell(c++);
        cell11.setCellValue(extraInfo);
        cell11.setCellStyle(cellStyleCreator.getLightGreenBackgroundCellStyle(workbook));

        c = createMultipleCells(row, c,2);


        Cell cell1 = row.createCell(c++);
        cell1.setCellValue(currencyAmountDTO.getCurrency());
        cell1.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));
        row.createCell(c++);

        Cell cell2 = row.createCell(c++);
        cell2.setCellValue(currencyAmountDTO.getAmount());
        cell2.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));
        row.createCell(c++);

        Cell cell3 = row.createCell(c++);
        cell3.setCellFormula(currencyAmountDTO.getFormulaDinProtivr());
        cell3.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));
        row.createCell(c);

    }

    private void mergeRegionsSecondPart(XSSFSheet sheet, int i, int j) {
        while(i<j) {
            sheet.addMergedRegion(new CellRangeAddress(i, i, 2, 7));
            sheet.addMergedRegion(new CellRangeAddress(i, i, 8, 9));
            sheet.addMergedRegion(new CellRangeAddress(i, i, 10, 11));
            sheet.addMergedRegion(new CellRangeAddress(i, i, 12, 13));

            i++;

        }
    }

    private void createAllowancesInTable(DecisionForABusinessTrip decisionForABusinessTrip, XSSFWorkbook workbook, XSSFSheet sheet) {
        int number = 0;
        CurrencyAmountDTO currencyAmountRSD = new CurrencyAmountDTO("RSD",2425.00,2425.00,"","K19");
        double dailyAllowanceEUR =decisionForABusinessTrip.getDailyAllowanceEUR();
        double dailyAllowanceInRsd = dailyAllowanceEUR*calculationDTO.getMiddleCourse();
        CurrencyAmountDTO currencyAmountEUR = new CurrencyAmountDTO("EUR",dailyAllowanceEUR,dailyAllowanceInRsd,"","K20*M3");

        CurrencyAmountDTO currencyAmountRSDSum = new CurrencyAmountDTO("RSD",0.00,0.00,"I16*K19","I16*K19");
        double numberDailyAllowance =calculationDTO.getNumberDailyAllowancesAbroad();
        CurrencyAmountDTO currencyAmountEURSum = new CurrencyAmountDTO("EUR",dailyAllowanceEUR*numberDailyAllowance,dailyAllowanceInRsd*numberDailyAllowance,"I17*K20","K22*M3");

        createOneRowInTableAllowance("2." + ++number,"Visina dnevnice u zemlji",currencyAmountRSD, sheet, workbook);
        createOneRowInTableAllowance("2."+ ++number,"Visina dnevnice u inostranstvu", currencyAmountEUR, sheet, workbook);
        createOneRowInTableCurrencies("2."+ ++number,"Iznos dnevnica u zemlji (1.8. x 2.1.)", currencyAmountRSDSum, sheet, workbook);
        createOneRowInTableCurrencies("2." + ++number,"Iznos dnevnica u inostranstvu (1.9. x 2.2.)", currencyAmountEURSum, sheet, workbook);
        createOneRowInTableSum("2." + ++number,"Ukupno dnevnice (2.3 + 2.4.)", "SUM(M21,M22)", sheet, workbook);

    }

    private void createOneRowInTableAllowance(String number, String title, CurrencyAmountDTO currencyAmountDTO, XSSFSheet sheet, XSSFWorkbook workbook) {
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);
        Cell cell0 = row.createCell(c++);
        cell0.setCellValue(number);
        cell0.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));

        Cell cell = row.createCell(c++);
        cell.setCellValue(title);
        c = createMultipleCells(row, c,5);
        Cell cell1 = row.createCell(c++);
        cell1.setCellValue(currencyAmountDTO.getCurrency());
        cell1.setCellStyle(cellStyleCreator.getCenteredCellStyle(workbook));
        row.createCell(c++);

        Cell cell2 = row.createCell(c++);
        cell2.setCellValue(currencyAmountDTO.getAmount());
        cell2.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));
        row.createCell(c++);

        Cell cell3 = row.createCell(c++);
        cell3.setCellFormula(currencyAmountDTO.getFormulaDinProtivr());
        cell3.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));
        row.createCell(c);
    }

    private void createRow2_0( XSSFWorkbook workbook, XSSFSheet sheet) {
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);

        c = createMultipleCells(row, c,7);
        Cell cell = row.createCell(c++);
        cell.setCellValue("valuta");
        cell.setCellStyle(cellStyleCreator.getCenteredCellStyle(workbook));
        row.createCell(c++);

        Cell cell2 = row.createCell(c++);
        cell2.setCellValue("iznos");
        cell2.setCellStyle(cellStyleCreator.getCenteredCellStyle(workbook));
        row.createCell(c++);

        Cell cell3 = row.createCell(c++);
        cell3.setCellValue("din. protivr.");
        cell3.setCellStyle(cellStyleCreator.getCenteredCellStyle(workbook));
        row.createCell(c);
    }

    private void createBasinInfoInTable(DecisionForABusinessTrip decisionForABusinessTrip, XSSFWorkbook workbook, XSSFSheet sheet) {
        int number = 0;

        List<TravelDetails> travelDetailsList = new ArrayList<>(travelDetailsService.findByTravelReqID(decisionForABusinessTrip.getTravelReqID()));
        travelDetailsList.sort(Comparator.comparing(TravelDetails::getDepartureDate));
        TravelDetails firstTravel =travelDetailsList.get(0);
        TravelDetails lastTravel =travelDetailsList.get(travelDetailsList.size()-1);

        String dateTimeStart = timeAndDateParser.getDateTimeAsStringFromTravelDetails(firstTravel);
        String firstBorderCross = timeAndDateParser.getDateTimeAsStringFromTravelDetailsBorder(firstTravel,3);
        String dateTimeEnd = timeAndDateParser.getDateTimeAsStringFromTravelDetails(lastTravel);
        String lastBorderCross = timeAndDateParser.getDateTimeAsStringFromTravelDetailsBorderLast(lastTravel,2,35);

        Double travelDurationInHours =hoursAndMoneyCounter.countDurationInHours(firstTravel,lastTravel);
        Double travelDurationInSerbia = 4.58;
        Double travelDurationAbroad = travelDurationInHours-travelDurationInSerbia;

        Double countDailyAllowanceSerbia = 0.00;
        Double countDailyAllowanceAbroad= hoursAndMoneyCounter.countDailyAllowance(firstTravel,lastTravel);
        calculationDTO.setNumberDailyAllowancesAbroad(countDailyAllowanceAbroad);

        createOneRowInTable("1." + ++number,"Datum i sat početka putovanja", dateTimeStart, sheet, workbook);
        createOneRowInTable("1."+ ++number,"Datum i sat prelaska granice u odlasku", firstBorderCross, sheet, workbook);
        createOneRowInTable("1."+ ++number,"Datum i sat prelaska granice u dolasku", lastBorderCross, sheet, workbook);


        createOneRowInTable("1." + ++number,"Datum i sat završetka putovanja", dateTimeEnd, sheet, workbook);
        createOneRowInTableDouble("1." + ++number,"Ukupan broj sati proveden na putu (1.4.-1.1.)", travelDurationInHours, sheet, workbook);
        createOneRowInTableDouble("1." + ++number,"Broj sati na putu u zemlji (1.2.-1.1.) + (1.4.-1.3.)", travelDurationInSerbia, sheet, workbook);

        createOneRowInTableDouble("1." + ++number,"Broj sati na putu u inostranstvu (1.3.-1.2.)", travelDurationAbroad, sheet, workbook);
        createOneRowInTableDouble("1." + ++number,"Broj dnevnica u zemlji (1.6. :24)", countDailyAllowanceSerbia, sheet, workbook);
        createOneRowInTableDouble("1."+ ++number,"Broj dnevnica u inostranstvu (1.8. :24)", countDailyAllowanceAbroad, sheet, workbook);



    }

    private void createOneRowInTableDouble(String number, String title, Double content, XSSFSheet sheet, XSSFWorkbook workbook) {
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);
        Cell cell0 = row.createCell(c++);
        cell0.setCellValue(number);
        cell0.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT));


        Cell cell = row.createCell(c++);
        cell.setCellValue(title);
        c = createMultipleCells(row, c,5);
        Cell cell6 = row.createCell(c++);
        cell6.setCellValue(content);
        cell6.setCellStyle(cellStyleCreator.getFormattedAlignedNumber(workbook));


        createMultipleCells(row, c,5);
    }



    private void mergeRegions(XSSFSheet sheet, int i, int j) {
        while(i<j) {

            sheet.addMergedRegion(new CellRangeAddress(i, i, 2, 7));
            sheet.addMergedRegion(new CellRangeAddress(i,i,8,13));
            i++;

        }

    }
    private void drawBorders(PropertyTemplate propertyTemplate, int i, int j) {

        while(i<j) {
            propertyTemplate.drawBorders(new CellRangeAddress(i, i, 1, 1), BorderStyle.THIN, BorderExtent.OUTSIDE);
            propertyTemplate.drawBorders(new CellRangeAddress(i, i, 8, 13), BorderStyle.THIN, BorderExtent.OUTSIDE);
            propertyTemplate.drawBorders(new CellRangeAddress(i, i, 2, 7), BorderStyle.THIN, BorderExtent.OUTSIDE);
            i++;
        }

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
        c = createMultipleCells(row, c,5);
        Cell cell6 = row.createCell(c++);
        cell6.setCellValue(content);

        cell6.setCellStyle(cellStyleCreator.getCenteredCellStyle(workbook));

        createMultipleCells(row, c,5);

    }

    private void createBasicInformationTable(DecisionForABusinessTrip decisionForABusinessTrip, PropertyTemplate propertyTemplate, XSSFWorkbook workbook, XSSFSheet sheet) {

        createSynechronTitle(decisionForABusinessTrip, workbook, sheet);

        createDocumentPurpose(workbook, sheet,decisionForABusinessTrip);

        sheet.addMergedRegion(new CellRangeAddress(4, 4, 1, 13));
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 13));
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 13));
    }
    private void createSynechronTitle(DecisionForABusinessTrip decisionForABusinessTrip, XSSFWorkbook workbook, XSSFSheet sheet) {
        sheet.createRow(row_counter++);
        Row row = sheet.createRow(row_counter++);
        int c = 0;
        row.createCell(c++);

        Cell cell = row.createCell(c++);
        cell.setCellValue("SYNECHRON SRB doo, Novi Sad");
        cell.setCellStyle(cellStyleCreator.getLeftBoldUnderlinedCellStyle(workbook));
        createMultipleCells(row, c,12);

        createMiddleCourse(workbook, sheet);

        sheet.createRow(row_counter++);

        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 13));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 10));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 12, 13));

    }

    private void createMiddleCourse(XSSFWorkbook workbook, XSSFSheet sheet) {
        int c;
        Row row = sheet.createRow(row_counter++);
        c=0;
        row.createCell(c++);
        Cell cell1 = row.createCell(c++);
        cell1.setCellValue("(Naziv firme)");
        cell1.setCellStyle(cellStyleCreator.getLeftTopSmallCellStyle(workbook));

        c = createMultipleCells(row, c,9);
        Cell cell2 = row.createCell(c++);
        cell2.setCellValue("Kurs:");
        cell2.setCellStyle(cellStyleCreator.getAlignedCellStyle(workbook,HorizontalAlignment.RIGHT));

        LocalDateTime date = TimeAndDateParser.getStringLocalDateTime(LocalDate.now());

        double middleCourse = nbsService.getMiddleCourseForDate(date, "EUR");
        calculationDTO.setMiddleCourse(middleCourse);
        Cell cell3 = row.createCell(c++);
        cell3.setCellValue(middleCourse);
        cell3.setCellStyle(cellStyleCreator.getRightWithGreenBackgroundCellStyle(workbook));

        row.createCell(c);
    }

    private void createDocumentPurpose(XSSFWorkbook workbook, XSSFSheet sheet,DecisionForABusinessTrip decisionForABusinessTrip) {
        List<DecisionTripInfo> decisionTripInfoList = new ArrayList<>(decisionForABusinessTrip.getDecisionTripInfo());
        decisionTripInfoList.sort(Comparator.comparing(p -> TimeAndDateParser.parseDate2(p.getDepartureDate())));
        DecisionTripInfo firstTrip = decisionTripInfoList.get(0);
        getOneRowOfPurpose(workbook, sheet, "OBRAČUN");
        getOneRowOfPurpose(workbook, sheet, "PO NALOGU ZA SLUŽBENI PUT U INOSTRANSTVO");
        getOneRowOfPurpose(workbook, sheet, "broj "+decisionForABusinessTrip.getDecisionNumber() + " _0_ " + firstTrip.getArrivalCountry()+", " + firstTrip.getArrivalCity());

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


    private int createMultipleCells(Row row, int c, int number) {
        int i =0;
        while(i<number) {
            row.createCell(c++);
            i++;
        }
        return c;
    }
}
