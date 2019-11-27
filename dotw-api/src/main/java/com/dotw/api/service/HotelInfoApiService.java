package com.dotw.api.service;

import com.alibaba.fastjson.JSONObject;
import com.dotw.api.common.DCMLHandler;
import com.dotw.core.domain.HotelInfo;
import com.dotw.core.domain.query.HotelQuery;
import com.dotw.core.service.HotelInfoService;
import com.dotw.core.util.CommonUtil;
import lombok.extern.apachecommons.CommonsLog;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by toby on 2019/11/22.
 */
@Service
@CommonsLog
public class HotelInfoApiService {

    @Autowired
    private HotelInfoService hotelInfoService;
    @Autowired
    private DCMLHandler dcmlHandler;

    public void syncBasicData() {
        try {
            hotelInfoService.syncByExcel();
            log.info("hotel sync success");
        } catch(Exception e) {
            log.info(e.getMessage());
        }
    }

    public void syncBatchData() {
        try {
            hotelInfoService.syncByExcelForBatch();
            log.info("hotel sync batch success");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public JSONObject searchHotel(String ids) {
        String fromDate = DateTime.now().plusDays(1).toString("yyyy-MM-dd");
        String toDate = DateTime.now().plusDays(30).toString("yyyy-MM-dd");
        List<String> idArray = Arrays.asList(ids.split(","));
        return dcmlHandler.searchHotelByID(idArray, fromDate, toDate);
    }

    public JSONObject searchHotelByCountryAndCity(String country, String city, int page, int size) {
        HotelQuery query = new HotelQuery();
        query.setCountry(country);
        query.setCity(city);
        Page<HotelInfo> hp = hotelInfoService.findAllByPageQuery(page, size, query);
        List<HotelInfo> hlist = hp.getContent();
        List<String> ids = new ArrayList<>();
        for (HotelInfo h : hlist) {
            ids.add(h.getDotwHotelCode());
        }
        String fromDate = DateTime.now().plusDays(1).toString("yyyy-MM-dd");
        String toDate = DateTime.now().plusDays(30).toString("yyyy-MM-dd");
        return dcmlHandler.searchHotelByID(ids, fromDate, toDate);
    }

}
