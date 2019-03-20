package edu.capstone.t6.controller;

import edu.capstone.t6.model.Log;
import edu.capstone.t6.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logServ;

    @GetMapping("/list")
    public List<Log> listAll() {
        return logServ.listAll();
    }

    @GetMapping("/clean")
    public Long clean() {
        return logServ.deleteAll();
    }

}
