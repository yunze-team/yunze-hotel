package com.dotw.api.common;

import com.dotw.api.util.XmlTool;
import com.dotw.core.util.PasswordEncryption;
import lombok.extern.apachecommons.CommonsLog;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author lazyb
 * @create 2019/11/19
 * @desc
 **/
@CommonsLog
@Component
public class DCMLHandler {

    @Value("${dotw.username}")
    private String DOTWUserName;
    @Value("${dotw.password}")
    private String DOTWPassword;
    @Value("${dotw.id}")
    private String DOTWCompanyCode;
    @Value("${dotw.url}")
    private String DOTWUrl;

    public Document generateBaseRequest() {
        Document doc = DocumentHelper.createDocument();
        Element customer = doc.addElement("customer");
        Element username = customer.addElement("username");
        username.setText(DOTWUserName);
        Element password = customer.addElement("password");
        password.setText(PasswordEncryption.BCRYPT.MD5(DOTWPassword));
        Element id = customer.addElement("id");
        id.setText(DOTWCompanyCode);
        Element source = customer.addElement("source");
        source.setText("1");
        return doc;
    }

    public Document getAllCountries() {
        Document doc = generateBaseRequest();
        Element customer = doc.getRootElement();
        Element request = customer.addElement("request");
        request.addAttribute("command", "getallcountries");
        Element reEl = request.addElement("return");
        Element fieldsEl = reEl.addElement("fields");
        fieldsEl.addElement("field").setText("regionName");
        fieldsEl.addElement("field").setText("regionCode");
        log.info(doc.asXML());
        return doc;
    }

    public Document getAllCitiesByCountry(String countryCode, String countryName) {
        Document doc = generateBaseRequest();
        Element customer = doc.getRootElement();
        Element request = customer.addElement("request");
        request.addAttribute("command", "getallcities");
        Element reEl = request.addElement("return");
        Element filtersEl = reEl.addElement("filters");
        filtersEl.addElement("countryCode").setText(countryCode);
        filtersEl.addElement("countryName").setText(countryName);
        Element fieldsEl = reEl.addElement("fields");
        fieldsEl.addElement("field").setText("countryName");
        fieldsEl.addElement("field").setText("countryCode");
        log.info(doc.asXML());
        return doc;
    }

    public String sendDotw(Document doc) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        HttpEntity<String> xmlEntity = new HttpEntity<>(doc.asXML(), headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://" + DOTWUrl, xmlEntity, String.class);
        log.info(responseEntity);
        return XmlTool.documentToJSONObject(responseEntity.getBody()).toJSONString();
    }

    public Document getAllCurrencies() {
        Document doc = generateBaseRequest();
        Element customer = doc.getRootElement();
        Element request = customer.addElement("request");
        request.addAttribute("command", "getcurrenciesids");
        log.info(doc.asXML());
        return doc;
    }

}
