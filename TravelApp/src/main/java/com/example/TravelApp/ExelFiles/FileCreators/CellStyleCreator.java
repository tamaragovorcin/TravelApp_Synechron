package com.example.TravelApp.ExelFiles.FileCreators;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CellStyleCreator {

    @Autowired
    FontCreator fontCreator;

    public XSSFCellStyle getTopCenteredSmallCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getAlignedCellStyle(workbook,HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
        cellStyle.setFont(fontCreator.getSmallFont(workbook));
        return cellStyle;
    }
    public XSSFCellStyle getLeftGreenBoldUnderlinedCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getAlignedCellStyle(workbook,HorizontalAlignment.LEFT);
        cellStyle.setFont(fontCreator.getUnderlinedBoldFont(workbook));
        getLightGreenBackground(cellStyle, IndexedColors.LIGHT_GREEN);
        return cellStyle;
    }

    private void getLightGreenBackground(XSSFCellStyle cellStyle, IndexedColors lightGreen) {
        cellStyle.setFillForegroundColor(lightGreen.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }

    public XSSFCellStyle getRightGreenBoldItalicCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getAlignedCellStyle(workbook,HorizontalAlignment.RIGHT);
        cellStyle.setFont(fontCreator.getItalicBoldFont(workbook));
        getLightGreenBackground(cellStyle, IndexedColors.LIGHT_GREEN);
        return cellStyle;
    }

    public XSSFCellStyle getLeftTopSmallCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getAlignedCellStyle(workbook,HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
        cellStyle.setFont(fontCreator.getSmallFont(workbook));
        return cellStyle;
    }

    public CellStyle getGreenBackgroundCellStyle(XSSFWorkbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cellStyle;
    }
    public XSSFCellStyle getLightGreenBackgroundCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        getLightGreenBackground(cellStyle, IndexedColors.LIGHT_GREEN);
        return cellStyle;
    }
    public XSSFCellStyle getRightWithGreenBackgroundCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT);
        getLightGreenBackground(cellStyle, IndexedColors.LIGHT_GREEN);
        return cellStyle;
    }
    public XSSFCellStyle getUnderlinedCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(fontCreator.getUnderlinedFont(workbook));
        return cellStyle;
    }

    public XSSFCellStyle getRightAquaBoldCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getAlignedCellStyle(workbook, HorizontalAlignment.RIGHT);
        getLightGreenBackground(cellStyle, IndexedColors.AQUA);
        cellStyle.setFont(fontCreator.getBoldFont(workbook));
        return cellStyle;
    }

    public XSSFCellStyle getLeftGreenBackgroundCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getAlignedCellStyle(workbook,HorizontalAlignment.LEFT);
        getLightGreenBackground(cellStyle, IndexedColors.LIGHT_GREEN);
        return cellStyle;
    }
    public XSSFCellStyle getAlignedCellStyle(XSSFWorkbook workbook, HorizontalAlignment alignment) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(alignment);
        return cellStyle;
    }
    public CellStyle getCenteredBoldCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getAlignedCellStyle(workbook, HorizontalAlignment.CENTER);
        cellStyle.setFont(fontCreator.getBoldSizedFont(workbook));
        return cellStyle;
    }

    public CellStyle getCenteredCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getAlignedCellStyle(workbook, HorizontalAlignment.CENTER);
        cellStyle.setFont(fontCreator.getNotBoldFont(workbook));
        return cellStyle;
    }

    public CellStyle getRightItalic(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getAlignedCellStyle(workbook,HorizontalAlignment.RIGHT);
        cellStyle.setFont(fontCreator.getItalic(workbook));
        return cellStyle;
    }

    public CellStyle getLeftBoldUnderlinedCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getAlignedCellStyle(workbook,HorizontalAlignment.LEFT);
        cellStyle.setFont(fontCreator.getUnderlinedBoldFont(workbook));
        return cellStyle;
    }

    public CellStyle getBoldedCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(fontCreator.getBoldFont(workbook));
        return  cellStyle;
    }

    public CellStyle getLeftGreenBoldCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        getLightGreenBackground(cellStyle, IndexedColors.AQUA);

        cellStyle.setFont(fontCreator.getBoldFont(workbook));
        return  cellStyle;
    }

    public CellStyle getAquaCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        getLightGreenBackground(cellStyle, IndexedColors.AQUA);
        return  cellStyle;
    }

    public XSSFCellStyle getFormattedNumber(XSSFWorkbook workbook) {
        XSSFCellStyle cs = workbook.createCellStyle();
        XSSFDataFormat df = workbook.
                createDataFormat();
        cs.setDataFormat(df.getFormat("#,##0.00"));
        return cs;
    }

    public CellStyle getFormattedAlignedNumber(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getFormattedNumber(workbook);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(fontCreator.getNotBoldFont(workbook));
        return cellStyle;
    }

    public CellStyle getCenteredBoldFormattedNormalCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getFormattedNumber(workbook);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(fontCreator.getBoldFont(workbook));
        return cellStyle;
    }

    public CellStyle getRightBoldedFormattedCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getFormattedNumber(workbook);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setFont(fontCreator.getBoldFont(workbook));
        return cellStyle;
    }

    public CellStyle getFormattedRightWithGreenBackgroundCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getFormattedNumber(workbook);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        getLightGreenBackground(cellStyle, IndexedColors.LIGHT_GREEN);
        return cellStyle;
    }

    public CellStyle getFormattedRightGreenBackgroundCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getFormattedNumber(workbook);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        getLightGreenBackground(cellStyle, IndexedColors.BRIGHT_GREEN);
        return cellStyle;
    }

    public CellStyle getFormattedRightItalic(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = getFormattedNumber(workbook);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setFont(fontCreator.getItalic(workbook));
        return cellStyle;
    }
}
