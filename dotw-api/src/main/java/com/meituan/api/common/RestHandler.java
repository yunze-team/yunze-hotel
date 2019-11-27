package com.meituan.api.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class RestHandler {

    @Autowired
    private RestTemplate restTemplate;
}
