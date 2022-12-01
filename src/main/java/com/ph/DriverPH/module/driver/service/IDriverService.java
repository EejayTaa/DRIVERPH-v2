package com.ph.DriverPH.module.driver.service;


import com.ph.DriverPH.module.driver.entity.Driver;
import com.ph.DriverPH.module.driver.request.DriverRequest;
import org.springframework.data.domain.PageRequest;

import java.util.List;


/**
 * @author Eejay Taa
 */
public interface IDriverService {

    void addDriver(DriverRequest request);

    Driver findDriverById(String driverId);

    void deleteDriverById(String driverId);

    void updateDriverById(String driverId, DriverRequest request);

    List<Driver> getDrivers(PageRequest of);
}
