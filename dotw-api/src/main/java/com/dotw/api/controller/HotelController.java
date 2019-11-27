package com.dotw.api.controller;

import com.dotw.api.service.HotelInfoApiService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lazyb
 * @create 2019/11/27
 * @desc
 **/
@RestController
@RequestMapping(value = "/hotel")
@CommonsLog
public class HotelController {

    @Autowired
    private HotelInfoApiService hotelInfoApiService;

    @GetMapping("/search")
    public Object searchHotel(String ids) {
        return hotelInfoApiService.searchHotel(ids);
    }

    @GetMapping("/all")
    public Object allHotel(String country, String city, int page, int size) {
        return hotelInfoApiService.searchHotelByCountryAndCity(country, city, page, size);
    }

}
