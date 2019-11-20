package com.dotw.api.service;

import com.dotw.api.common.DCMLHandler;
import com.dotw.core.service.CountryService;
import lombok.extern.apachecommons.CommonsLog;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String getAllCountries() {
        log.info("getAllCountries start.");
        Document doc = dcmlHandler.getAllCountries();
        return dcmlHandler.sendDotw(doc);
    }

    public void syncCountries() {
        log.info("syncCountries start.");
        Document doc = dcmlHandler.getAllCountries();
        String json = dcmlHandler.sendDotw(doc);
        countryService.syncByJson(json);
    }

}
