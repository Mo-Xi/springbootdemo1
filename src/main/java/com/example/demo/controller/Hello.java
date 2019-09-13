package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Hello {

    @GetMapping("/index")
    public String hello(@RequestParam(name = "name",required = false,defaultValue = "sss") String name, Model model){
        model.addAttribute("name",name);
        return "index";
    }

}
