package com.ph.DriverPH.module.driver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Driver {


    private String driverId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String company;

    private LocalDateTime date;

}
