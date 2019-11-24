package com.dotw.api.controller;

import com.dotw.api.service.HotelInfoApiService;
import com.dotw.api.service.InternalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lazyb
 * @create 2019/11/19
 * @desc
 **/
@RestController
public class IndexController {

    @Autowired
    private InternalCodeService internalCodeService;
    @Autowired
    private HotelInfoApiService hotelInfoApiService;

    @GetMapping("/")
    public String index() {
        return "dotw api started.";
    }

    @GetMapping("/synccountries")
    public String syncCountries() {
        internalCodeService.syncCountries();
        return "SUCCESS";
    }

    @GetMapping("/countries")
    public Object allCountries() {
        return internalCodeService.getAllCountries();
    }

    @GetMapping("/cities")
    public Object allCities() {
        return internalCodeService.getAllCities();
    }

    @GetMapping("/synccities")
    public String syncCities() {
        internalCodeService.syncCities();
        return "SUCCESS";
    }

    @GetMapping("/currencies")
    public Object allcurrencies() {
        return internalCodeService.getAllCurrencies();
    }

    @GetMapping("/synccurrencies")
    public String syncCurrencies() {
        internalCodeService.syncCurrencies();
        return "SUCCESS";
    }

    @GetMapping("/synchotels")
    public String allHotels() {
        hotelInfoApiService.syncBasicData();
        return "SUCCESS";
    }

}
