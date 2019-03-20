package edu.capstone.t6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "usage")
public class Usage {

    @Id
    @Column(name = "date")
    private Date date;

    @Column(name = "power")
    private Double powerWh;

    @Column(name = "water")
    private Double waterGal;

}
