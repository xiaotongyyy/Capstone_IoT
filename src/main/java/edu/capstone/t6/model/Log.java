package edu.capstone.t6.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="device_id")
    @JsonManagedReference // prevent infinite loop for find methods caused by bi-directional association
    private Device device;

    private Boolean runStat;

    private Timestamp time;

    public Log() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id)  {
        this.id = id;
    }

    public Device getDevice() {
        return this.device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Boolean getRunStat() {
        return this.runStat;
    }

    public void setRunStat(Boolean runStat) {
        this.runStat = runStat;
    }

    public Timestamp getTimestamp() {
        return this.time;
    }

    public void setTimestamp(Timestamp time) {
        this.time = time;
    }
}
