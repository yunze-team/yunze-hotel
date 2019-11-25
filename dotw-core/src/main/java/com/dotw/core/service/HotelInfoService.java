package com.dotw.core.service;

import com.dotw.core.domain.HotelInfo;
import com.dotw.core.redis.IRedisService;
import com.dotw.core.repository.HotelInfoRepository;
import com.dotw.core.util.CommonUtil;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    @Autowired
    private IRedisService redisService;

    @Value("${dotw.hotel.excel}")
    private String dataPath;
    @Value("${dotw.redis.list.key}")
    private String hotelListKey;

    /**
     * 通过dotw给的excel数据更新酒店基础数据
     */
    @Transactional
    public void syncByExcel() throws Exception {
        List<HotelInfo> list = commonUtil.excelToHotelBean(dataPath);
        int i = 0;
        for (HotelInfo hotelInfo : list) {
            if (hotelInfoRepository.findByDotwHotelCode(hotelInfo.getDotwHotelCode()) != null) {
                continue;
            }
            i++;
            hotelInfoRepository.save(hotelInfo);
        }
        log.info("sync hotel items size: " + i);
    }

    /**
     * 10w+批量同步，不考虑重复问题，提高效率
     * @throws Exception
     */
    @Transactional
    public void syncByExcelForBatch() throws Exception {
        List<HotelInfo> list = new ArrayList<>();
        // 先存入redis，重复读取excel很花时间
        list = redisService.getList(hotelListKey, HotelInfo.class);
        if (list == null || list.size() == 0) {
            list = commonUtil.excelToHotelBean(dataPath);
            redisService.setList(hotelListKey, list);
        }
        int i = 0;
        for (HotelInfo hotelInfo : list) {
            i++;
            hotelInfoRepository.save(list);
        }
        log.info("sync batch hotel items size: " + i);
    }

}
