package com.turkcell.consulschoolservice.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author sercan
 * Created by IntelliJ at 14.01.2018
 */


@FeignClient("student-service")
public interface StudentClient {
    @RequestMapping(value = "/getStudentDetailsForSchool/{schoolname}", method = RequestMethod.GET)
    String getStudentsForSchool(@PathVariable("schoolname") String schoolName);
}
