package com.dotw.admin.controller;

import com.dotw.core.domain.HotelInfo;
import com.dotw.core.service.HotelInfoService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lazyb
 * @create 2019/11/26
 * @desc
 **/
@RestController
@CommonsLog
public class HotelController {

    @Autowired
    private HotelInfoService hotelInfoService;

    @GetMapping("/hotel/list")
    public Map<String, Object> getAllHotel(int page, int rows, String country) {
        Map<String, Object> resMap = new HashMap<>();
        Page<HotelInfo> ph = hotelInfoService.findAllByPageQuery(page, rows, country);
        resMap.put("total", ph.getTotalElements());
        resMap.put("rows", ph.getContent());
        return resMap;
    }

}
