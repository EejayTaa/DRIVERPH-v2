package com.ph.DriverPH.module.driver.service;


import com.ph.DriverPH.module.driver.entity.Driver;
import com.ph.DriverPH.module.driver.request.DriverRequest;
import com.ph.DriverPH.module.driver.response.DriverDetailResponse;

import java.util.List;


public interface IDriverService {

    void saveDriver(DriverRequest request);

    DriverDetailResponse findDriverById(String driverId);

    void deleteDriverById(String driverId);

    void updateDriverById(String driverId, DriverRequest request);

    List<DriverDetailResponse> findAllDrivers(Integer page, Integer size);
}
