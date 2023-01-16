package com.ph.DriverPH.module.driver.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class DriverDetailResponse implements Serializable {


    private String driverId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String company;

    private LocalDateTime date;
}
