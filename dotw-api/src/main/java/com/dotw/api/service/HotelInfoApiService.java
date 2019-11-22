package com.dotw.api.service;

import com.dotw.core.domain.HotelInfo;
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

    @Value("${dotw.hotel.excel}")
    private String dataPath;

    @Autowired
    private CommonUtil commonUtil;

    public List<HotelInfo> getAllByExcel() {
        List<HotelInfo> list = new ArrayList<>();
        try {
            list = commonUtil.excelToHotelBean(dataPath);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return list;
    }

}
