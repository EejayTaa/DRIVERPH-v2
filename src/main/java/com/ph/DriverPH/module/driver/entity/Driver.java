package com.ph.DriverPH.module.driver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ph.DriverPH.common.entity.BaseEntity;
import com.ph.DriverPH.module.auth.entity.BaseInformation;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity(name="driver")
public class Driver extends BaseEntity {

    private String driverId;
    private BaseInformation baseInformation;
    private String company;

}
