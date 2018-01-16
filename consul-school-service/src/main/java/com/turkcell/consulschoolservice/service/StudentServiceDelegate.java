package com.turkcell.consulschoolservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @author sercan
 * Created by IntelliJ at 12.01.2018
 */

@Service
public class StudentServiceDelegate {
    @Autowired
    RestTemplate restTemplate;

    public String callStudentServiceAndGetData(String schoolName){
        System.out.println("Consul Demo - Getting School details for " + schoolName);

        String response = restTemplate.exchange("http://student-service/getStudentDetailsForSchool/{schoolname}"
                , HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, schoolName).getBody();

        System.out.println("Response received as " + response + " - " + new Date());

        return "School Name - " + schoolName + " ::: Student Details " + response + " - " + new Date();
    }

}
