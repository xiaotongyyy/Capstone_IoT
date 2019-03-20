package edu.capstone.t6.controller;

import edu.capstone.t6.model.Usage;
import edu.capstone.t6.service.UsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsageController {

    @Autowired
    private UsageService usageServ;

    @GetMapping("/list")
    public List<Usage> listAll() {
        return usageServ.listAll();
    }
}
