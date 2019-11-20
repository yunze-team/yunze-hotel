package com.dotw.api.controller;

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

    @GetMapping("/")
    public String index() {
        return "dotw api started.";
    }

    @GetMapping("/countries")
    public String allCountries() {
        return internalCodeService.getAllCountries();
    }

}
