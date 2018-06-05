package com.practice.bank.controllers;

import com.practice.bank.model.FirstName;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainContlroller
{
    @GetMapping(name = "/testingapi")
    @ResponseBody
    public FirstName testing()
    {
        return new FirstName("John");
    }
}
