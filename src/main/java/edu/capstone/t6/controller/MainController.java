package edu.capstone.t6.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @RequestMapping("/")
    public String home() {
        return "This is root page.";
    }

}
