package edu.capstone.t6.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Calculation {

    public Map<String,Double> calculate(String devType, Integer ratedPower, Integer ratedWater, Long runTime) {

        double powerUsed = 0.0;
        double waterUsed = 0.0;
        Map res = new HashMap();
        System.out.println(devType);

        switch(devType) {
            case "Bulb" :
                System.out.println("Bulb");
                powerUsed = this.generalPowEq(ratedPower, runTime);
                break;

        }
        System.out.println("powerUsed");
        res.put("power", powerUsed);
        res.put("water", waterUsed);
        return res;
    }

    /**
     * Universal equation of power usage for most electronic devices.
     * @param ratedPower rated power in Watt
     * @param runTime running time in second
     * @return power usage in Watt-hour
     */
    private Double generalPowEq(Integer ratedPower, Long runTime) {
        Double t = runTime.doubleValue()/3600.0;
        return ratedPower.doubleValue()*t;
    }

}
