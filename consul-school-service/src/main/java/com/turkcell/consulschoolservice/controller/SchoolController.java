package com.turkcell.consulschoolservice.controller;

import com.turkcell.consulschoolservice.feign.StudentClient;
import com.turkcell.consulschoolservice.service.StudentServiceDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sercan
 * Created by IntelliJ at 12.01.2018
 */

@RestController
public class SchoolController {

    @Autowired
    StudentServiceDelegate studentServiceDelegate;

    @Autowired
    StudentClient studentClient;

    @GetMapping(value = "/getSchoolDetails/{schoolname}")
    public String getStudents(@PathVariable String schoolname)
    {
        System.out.println("Going to call student service to get data!");
//        return studentServiceDelegate.callStudentServiceAndGetData(schoolname);
        return studentClient.getStudentsForSchool(schoolname);
    }

}
