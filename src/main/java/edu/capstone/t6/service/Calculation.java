package edu.capstone.t6.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Calculation {

    /* For demo purpose only! */
    private static final double DEMOFACTOR = 1.0;

    public Map<String,Double> calculate(String devType, Integer ratedPower, Integer ratedWater, Long runTime) {

        double powerUsed = 0.0; // in watt
        double waterUsed = 0.0; // in gallon
        double hotRatio = 0.0; // [0,1]
        double cycle = 0.0; // in minute

        Map res = new HashMap();
        switch(devType) {
            case "Bulb":
                powerUsed = this.generalPowEq(ratedPower, runTime);
                break;
            case "TVL":
                powerUsed = this.generalPowEq(ratedPower, runTime);
                break;
            case "TVB":
                powerUsed = this.generalPowEq(ratedPower, runTime);
                break;
            case "Stove":
                powerUsed = this.generalPowEq(ratedPower, runTime);
                break;
            case "Oven":
                powerUsed = this.generalPowEq(ratedPower, runTime);
                break;
            case "Microwave":
                powerUsed = this.generalPowEq(ratedPower, runTime);
                break;
            case "DishWasher":
                cycle = 45.0;
                waterUsed = 6.0;
                hotRatio = 1.0;
                powerUsed = ratedPower.doubleValue() * (cycle/60.0);
                break;
            case "ClothWasher":
                cycle = 30.0;
                waterUsed = 20.0;
                hotRatio = 0.85;
                powerUsed = ratedPower.doubleValue() * (cycle/60.0);
                break;
            case "Dryer":
                cycle = 30.0;
                powerUsed = ratedPower.doubleValue() * (cycle/60.0);
                break;
            case "Refrigerator":
                cycle = 8.0*60.0;
                powerUsed = ratedPower.doubleValue() * (cycle/60.0);
                break;
            case "Shower":
                waterUsed = 25.0;
                hotRatio = 0.65;
                break;
            case "Bath":
                waterUsed = 30.0;
                hotRatio = 0.65;
                break;
        }
        res.put("power", powerUsed);
        res.put("water", waterUsed);
        res.put("ratio", hotRatio);
        return res;
    }

    public double heater(Integer ratedPower, double hotWater) {
        double efficiency = 0.25; // 0.25 gallon per minute
        return ratedPower.doubleValue() * ((hotWater/efficiency)/60.0);
    }

    /**
     * Universal equation of power usage for most electronic devices.
     * @param ratedPower rated power in Watt
     * @param runTime running time in second
     * @return power usage in Watt-hour
     */
    private double generalPowEq(Integer ratedPower, Long runTime) {
        double t = runTime.doubleValue()/3600.0;
        return ratedPower.doubleValue()*t;
    }


}
