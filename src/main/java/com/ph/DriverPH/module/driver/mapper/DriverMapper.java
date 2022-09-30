package com.ph.DriverPH.module.driver.mapper;

import com.ph.DriverPH.module.driver.request.DriverRequest;
import com.ph.DriverPH.module.driver.response.DriverResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DriverMapper {
    List<DriverResponse> getDrivers();

    void addDriver(DriverRequest request);

    Optional<DriverResponse> findDriverById(String driverId);

    void deleteDriverById(String driverId);
}
