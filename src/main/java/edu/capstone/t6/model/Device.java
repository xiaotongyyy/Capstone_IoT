package edu.capstone.t6.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "device")
public class Device {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "power")
    private Integer power;
    @Column(name = "water")
    private Integer water;

    @OneToMany(mappedBy="device",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JsonBackReference // prevent infinite loop for find methods caused by bi-directional association
    private Set<Log> logs = new HashSet<Log>();


    public Device() {

    }

    public Device(Long id, String type, Integer power, Integer water) {
        this.id = id;
        this.type = type;
        this.power = power;
        this.water = water;
    }

    public Long getID() {
        return this.id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPower() {
        return this.power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getWater() {
        return this.water;
    }

    public void setWater(Integer water) {
        this.water = water;
    }

    public Set<Log> getLogs() {
        return this.logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }
}
