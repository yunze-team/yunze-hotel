package com.dotw.api.service;

import com.dotw.core.domain.HotelInfo;
import com.dotw.core.service.HotelInfoService;
import com.dotw.core.util.CommonUtil;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toby on 2019/11/22.
 */
@Service
@CommonsLog
public class HotelInfoApiService {

    @Autowired
    private HotelInfoService hotelInfoService;

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

}
