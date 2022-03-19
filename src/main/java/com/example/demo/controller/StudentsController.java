package com.example.demo.controller;


import com.example.demo.entity.Student;
import com.example.demo.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StudentsController {

    @Autowired
    private StudentsRepository studRepo;

    @GetMapping("/students")
    public ModelAndView show(){
        ModelAndView mav = new ModelAndView("studentsList");
        List<Student> studList=studRepo.findAll();
        mav.addObject("students",studList);
        return mav;
    }

    @GetMapping("/addStudent")
    public ModelAndView addStudent(){
        ModelAndView mav = new ModelAndView("addForm");
        Student newStudent=new Student();
        mav.addObject("student", newStudent);
        return mav;
    }

    @PostMapping("/saveStudent")
    public String save(@ModelAttribute Student stud){
        studRepo.save(stud);
        return "redirect:/students";
    }

    @GetMapping("/update")
    public ModelAndView update(@RequestParam Long studentId){
        ModelAndView mav = new ModelAndView("addForm");
        Student stud=studRepo.findById(studentId).get();
        mav.addObject("student",stud);
        return  mav;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long studentId){
        studRepo.deleteById(studentId);
        return "redirect:/students";
    }

}
