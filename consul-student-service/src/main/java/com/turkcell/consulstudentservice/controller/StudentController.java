package com.turkcell.consulstudentservice.controller;

import com.turkcell.consulstudentservice.domain.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sercan
 * Created by IntelliJ at 12.01.2018
 */

@RestController
public class StudentController {
    private static ConcurrentHashMap<String, List<Student>> schooDB = new ConcurrentHashMap<>();

    static {
        schooDB = new ConcurrentHashMap<>();

        List<Student> studentList = new ArrayList<>();
        Student student = new Student("Sercan", "Class IV");
        studentList.add(student);
        student = new Student("Aziz", "Class V");
        studentList.add(student);

        schooDB.putIfAbsent("School One", studentList);

        studentList =  new ArrayList<>();
        student = new Student("Kajal", "Class III");
        studentList.add(student);
        student = new Student("Sukesh", "Class VI");
        studentList.add(student);

        schooDB.putIfAbsent("School Two", studentList);
    }

    @GetMapping(value = "/getStudentDetailsForSchool/{schoolname}")
    public List<Student> getStudents(@PathVariable String schoolname) {
        System.out.println("Getting Student details for " + schoolname);

        List<Student> studentList = schooDB.get(schoolname);
        if (studentList == null) {
            studentList = new ArrayList<Student>();
            Student std = new Student("Not Found", "N/A");
            studentList.add(std);
        }
        return studentList;
    }
}
