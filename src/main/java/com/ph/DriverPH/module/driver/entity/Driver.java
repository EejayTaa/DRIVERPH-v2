package com.ph.DriverPH.module.driver.entity;


import com.ph.DriverPH.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity(name="driver")
public class Driver extends BaseEntity {

    private String driverId;

    private String firstName;

    private String middleName;

    private String lastName;

}
