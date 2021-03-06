package edu.capstone.t6.service;

import edu.capstone.t6.model.Device;
import edu.capstone.t6.model.Log;
import edu.capstone.t6.repository.DeviceRepository;
import edu.capstone.t6.repository.LogRepository;
import edu.capstone.t6.repository.UsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class DeviceService {

    private DeviceRepository devRepo;
    private LogRepository logRepo;
    private UsageRepository usageRepo;
    private Calculation calc;

    @Autowired
    public DeviceService(DeviceRepository devRepo, LogRepository logRepo,
                         UsageRepository usageRepo,Calculation calc) {
        this.devRepo = devRepo;
        this.logRepo = logRepo;
        this.usageRepo = usageRepo;
        this.calc = calc;
    }

    public List<Device> listAll() {
        return devRepo.findAllByOrderByIdAsc();
    }

    public Device add(Long id, String type, Integer power, Integer water) {
        Device d = new Device();
        d.setID(id);
        d.setType(type);
        d.setPower(power);
        d.setWater(water);
        return devRepo.save(d);
    }

    //@Transactional
    public boolean delete(Long id) {
        devRepo.deleteById(id);
        return !devRepo.existsById(id);
    }

    public void turnOn(Long id) {
        Log l = new Log();
        l.setDevice(devRepo.getOne(id));
        l.setRunStat(Boolean.TRUE);
        l.setTimestamp(new Timestamp(System.currentTimeMillis()));
        logRepo.save(l);
    }

    @Transactional
    public void turnOff(Long id) {
        Device d = devRepo.findTopById(id);
        Map<String, Double> usage;
        if (d.getType().equals("Bath") || d.getType().equals("Shower")
                || d.getType().equals("DishWasher") || d.getType().equals("ClothWasher")
                || d.getType().equals("Dryer") || d.getType().equals("Refrigerator")) {
            usage = calc.calculate(d.getType(), d.getPower(), d.getWater(), Long.valueOf(0));

        } else {
            /* Retrieve timestamp and calculate running time */
            Log lastLog = logRepo.findTopByRunStatAndDevice_Id(Boolean.TRUE, id);
            Long runTime = (System.currentTimeMillis() - lastLog.getTimestamp().getTime()) / 1000;
            /* Change runStat to turned off */
            logRepo.updateRunStatById(id, Boolean.FALSE);
            /* Check out */
            usage = calc.calculate(d.getType(), d.getPower(), d.getWater(), runTime);
        }
        double hotfix = usage.get("water") * usage.get("ratio");
        /* Update usage record */
        Date da = new Date(System.currentTimeMillis());
        usageRepo.updateUsageByDate(usage.get("power"), usage.get("water"), da);
        if(hotfix != 0) {
            heater(hotfix);
        }
    }

    public void heater(double hotfix) {
        Device d = devRepo.findTopByTypeIs("WaterHeater");
        double usage = calc.heater(d.getPower(), hotfix);
        /* Update usage record */
        Date da = new Date(System.currentTimeMillis());
        usageRepo.updateUsageByDate(usage, 0.0, da);
    }
}
