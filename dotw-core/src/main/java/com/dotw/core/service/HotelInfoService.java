package com.dotw.core.service;

import com.dotw.core.domain.HotelInfo;
import com.dotw.core.repository.HotelInfoRepository;
import com.dotw.core.util.CommonUtil;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lazyb
 * @create 2019/11/24
 * @desc
 **/
@Service
@CommonsLog
public class HotelInfoService {

    @Autowired
    private HotelInfoRepository hotelInfoRepository;
    @Autowired
    private CommonUtil commonUtil;

    @Value("${dotw.hotel.excel}")
    private String dataPath;

    /**
     * 通过dotw给的excel数据更新酒店基础数据
     */
    public void syncByExcel() throws Exception {
        List<HotelInfo> list = commonUtil.excelToHotelBean(dataPath);
        int i = 0;
        for (HotelInfo hotelInfo : list) {
            if (hotelInfoRepository.findByHotelCode(hotelInfo.getHotelCode()) != null) {
                continue;
            }
            i++;
            hotelInfoRepository.save(hotelInfo);
        }
        log.info("sync hotel items size: " + i);
    }

}
