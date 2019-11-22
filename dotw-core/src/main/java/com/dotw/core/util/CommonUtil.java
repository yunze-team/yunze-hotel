package com.dotw.core.util;

import com.dotw.core.domain.HotelInfo;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by toby on 2019/11/22.
 */
@Component
@CommonsLog
public class CommonUtil {

    public List<HotelInfo> excelToHotelBean(String path) throws Exception {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook excel = new XSSFWorkbook(is);
        HotelInfo hotelInfo = null;
        List<HotelInfo> list = new ArrayList<>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < excel.getNumberOfSheets(); numSheet++) {
            XSSFSheet sheet = excel.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                XSSFRow row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                hotelInfo = new HotelInfo();
                hotelInfo.setRegion(row.getCell(0).getStringCellValue());
                hotelInfo.setCountry(row.getCell(1).getStringCellValue());
                hotelInfo.setShortCountryName(row.getCell(2).getStringCellValue());
                hotelInfo.setCountryCode(row.getCell(3).getStringCellValue());
                hotelInfo.setCity(row.getCell(4).getStringCellValue());
                hotelInfo.setCityCode(row.getCell(5).getStringCellValue());
                hotelInfo.setHotelCode(row.getCell(6).getStringCellValue());
                hotelInfo.setHotelName(row.getCell(7).getStringCellValue());
                hotelInfo.setStarRating(row.getCell(8).getStringCellValue());
                hotelInfo.setReservationTelephone(row.getCell(9).getStringCellValue());
                hotelInfo.setHotelAddress(row.getCell(10).getStringCellValue());
                hotelInfo.setLatitude(row.getCell(11).getStringCellValue());
                hotelInfo.setLongitude(row.getCell(12).getStringCellValue());
                hotelInfo.setChainName(row.getCell(13).getStringCellValue());
                hotelInfo.setBrandName(row.getCell(14).getStringCellValue());
                hotelInfo.setNewProperty(row.getCell(15).getStringCellValue());
                list.add(hotelInfo);
            }
        }
        return list;
    }

}
