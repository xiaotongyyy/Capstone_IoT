package edu.capstone.t6.controller;

import edu.capstone.t6.model.Device;
import edu.capstone.t6.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService devServ;

    @GetMapping("/list")
    public List<Device> listAll() {
        return devServ.listAll();
    }

    @PutMapping("/add")
    public Device add(@RequestParam("id") Long id, @RequestParam("type") String type,
                      @RequestParam("power") Integer power, @RequestParam("water") Integer water) {
        return devServ.add(id, type, power, water);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return devServ.delete(id);
    }

    @PutMapping("/turn_on/{id}")
    public void turnOn(@PathVariable("id") Long id) {
        devServ.turnOn(id);
    }

    @PutMapping("/turn_off/{id}")
    public void tuenOff(@PathVariable("id") Long id) {
        devServ.turnOff(id);
    }
}
