package com.checkstatus.isthesiteup.controller;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class URLCheckController {

    private final String SITE_IS_UP = "Site is UP";
    private final String SITE_IS_DOWN = "Site is DOWN";
    public final String INCORRECT_URL = "URL is incorrect";


    @GetMapping("/checkIsAlive")
    public String checkIsAliveString(@RequestParam String url) {

        String returnMessage = "";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode()/100;
            if ( responseCodeCategory != 2 && responseCodeCategory != 3) {
                    returnMessage = SITE_IS_DOWN;
            }
            else{
                returnMessage = SITE_IS_UP;
            }



        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            returnMessage = SITE_IS_DOWN;
        } 
        return returnMessage;
    }
    

}
