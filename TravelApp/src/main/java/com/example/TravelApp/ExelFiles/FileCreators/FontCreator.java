package com.example.TravelApp.ExelFiles.FileCreators;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class FontCreator {

    public XSSFFont getSmallFont(XSSFWorkbook workbook) {
        XSSFFont fontStyle = workbook.createFont();
        fontStyle.setFontHeight(8);
        return fontStyle;
    }
    public XSSFFont getItalicBoldFont(XSSFWorkbook workbook) {
        XSSFFont fontStyle= getBoldFont(workbook);
        fontStyle.setItalic(true);
        return fontStyle;
    }

    public XSSFFont getUnderlinedBoldFont(XSSFWorkbook workbook) {
        XSSFFont fontStyle= getBoldFont(workbook);
        fontStyle.setUnderline(XSSFFont.U_SINGLE);
        fontStyle.setFontHeight(16);
        return fontStyle;
    }
    public XSSFFont getBoldSizedFont(XSSFWorkbook workbook) {
        XSSFFont fontStyle= getBoldFont(workbook);
        fontStyle.setFontHeight(14);
        return fontStyle;
    }

    public XSSFFont getBoldFont(XSSFWorkbook workbook) {
        XSSFFont fontStyle= workbook.createFont();
        fontStyle.setBold(true);
        return fontStyle;
    }
    public XSSFFont getUnderlinedFont(XSSFWorkbook workbook) {
        XSSFFont fontStyle= workbook.createFont();
        fontStyle.setUnderline(XSSFFont.U_SINGLE);
        return fontStyle;
    }

    public Font getItalic(XSSFWorkbook workbook) {
        XSSFFont fontStyle = workbook.createFont();
        fontStyle.setItalic(true);
        return fontStyle;
    }

    public Font getNotBoldFont(XSSFWorkbook workbook) {
        XSSFFont fontStyle= workbook.createFont();
        fontStyle.setBold(false);
        return fontStyle;
    }
}
