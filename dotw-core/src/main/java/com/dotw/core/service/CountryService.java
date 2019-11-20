package com.dotw.core.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dotw.core.domain.Country;
import com.dotw.core.repository.CountryRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lazyb
 * @create 2019/11/20
 * @desc
 **/
@Service
@CommonsLog
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public void syncByJson(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray jsonCountries = jsonObject.getJSONArray("countries");
        JSONObject jsonCountry = jsonCountries.getJSONObject(0);
        JSONArray countries = jsonCountry.getJSONArray("country");
        for (int i = 0; i < countries.size(); i++) {
            JSONObject joc = countries.getJSONObject(i);
            Country country = new Country(joc.getString("name"), joc.getString("code"),
                    joc.getString("regionName"), joc.getString("regionCode"));
            log.info(country.toString());
        }
    }

}
