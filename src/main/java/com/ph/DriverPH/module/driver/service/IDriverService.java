package com.ph.DriverPH.module.driver.service;

import com.ph.DriverPH.module.driver.request.DriverRequest;
import com.ph.DriverPH.module.driver.response.DriverResponse;

import java.util.List;
import java.util.Optional;

public interface IDriverService {
    List<DriverResponse> getDrivers();

    void addDriver(DriverRequest request);

    DriverResponse findDriverById(String driverId);

    void deleteDriverById(String driverId);
}
