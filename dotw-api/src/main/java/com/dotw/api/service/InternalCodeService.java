package com.dotw.api.service;

import com.dotw.api.common.DCMLHandler;
import com.dotw.core.domain.City;
import com.dotw.core.domain.Country;
import com.dotw.core.domain.Currency;
import com.dotw.core.service.CityService;
import com.dotw.core.service.CountryService;
import lombok.extern.apachecommons.CommonsLog;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lazyb
 * @create 2019/11/19
 * @desc
 **/
@Service
@CommonsLog
public class InternalCodeService {

    @Autowired
    private DCMLHandler dcmlHandler;
    @Autowired
    private CountryService countryService;
    @Autowired
    private CityService cityService;

    public List<Country> getAllCountries() {
        log.info("getAllCountries start.");
        return countryService.getAll();
    }

    public void syncCountries() {
        log.info("syncCountries start.");
        Document doc = dcmlHandler.getAllCountries();
        String json = dcmlHandler.sendDotw(doc);
        countryService.syncByJson(json);
    }

    public List<City> getAllCities() {
        return cityService.getAll();
    }

    public void syncCities() {
        log.info("sync cities start.");
        Country country = countryService.getChina();
        Document doc = dcmlHandler.getAllCitiesByCountry(country.getCode(), country.getName());
        String json = dcmlHandler.sendDotw(doc);
        cityService.syncByJson(json);
    }

    public List<Currency> getAllCurrencies() {
        log.info("getAllCurrencies start.");
        return countryService.getAllCurrency();
    }

    public void syncCurrencies() {
        log.info("sync currencies start.");
        Document doc = dcmlHandler.getAllCurrencies();
        String json = dcmlHandler.sendDotw(doc);
        countryService.syncCurrencyByJson(json);
    }

}
