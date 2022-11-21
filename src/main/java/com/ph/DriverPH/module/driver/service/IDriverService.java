package com.ph.DriverPH.module.driver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ph.DriverPH.module.driver.entity.Driver;
import com.ph.DriverPH.module.driver.request.DriverRequest;
import com.ph.DriverPH.module.driver.response.DriverResponse;


/**
 * @author Eejay Taa
 */
public interface IDriverService extends IService<Driver> {

    void addDriver(DriverRequest request);

    /**
     * @param driverId
     * @return
     */
    Driver findDriverById(String driverId);

    void deleteDriverById(String driverId);

    void updateDriverById(String driverId, DriverRequest request);

    Page<DriverResponse> getDrivers(Page<Driver> of);
}
