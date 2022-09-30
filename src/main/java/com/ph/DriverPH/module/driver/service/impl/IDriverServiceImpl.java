package com.ph.DriverPH.module.driver.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.ph.DriverPH.exception.ServiceException;
import com.ph.DriverPH.module.driver.mapper.DriverMapper;
import com.ph.DriverPH.module.driver.request.DriverRequest;
import com.ph.DriverPH.module.driver.response.DriverResponse;
import com.ph.DriverPH.module.driver.service.IDriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class IDriverServiceImpl implements IDriverService {

    @Autowired
    DriverMapper driverMapper;

    @Override
    public List<DriverResponse> getDrivers() {
        return driverMapper.getDrivers();
    }

    @Override
    public void addDriver(DriverRequest request) {

        //set created date
        LocalDateTime date = LocalDateTime.now();
        request.setDate(date);

        //set unique driver id
        request.setDriverId(this.generateDriverId());

        log.info("IDriverServiceImpl.addDriver", request);

        driverMapper.addDriver(request);

    }

    @Override
    public DriverResponse findDriverById(String driverId) {
        Optional<DriverResponse> driver = driverMapper.findDriverById(driverId);
        return driver.orElse(null);
    }

    @Override
    public void deleteDriverById(String driverId) {

        //check if driver exists in the database
        DriverResponse driver = findDriverById(driverId);
        if(Objects.isNull(driver)){
            throw new ServiceException("Driver not found.", HttpStatus.NOT_FOUND);
        }

        driverMapper.deleteDriverById(driverId);
    }

    @Override
    public void updateDriverById(String driverId, DriverRequest request) {

        //check if driver exists in the database
        DriverResponse driver = findDriverById(driverId);
        if(Objects.isNull(driver)){
            throw new ServiceException("Driver not found.", HttpStatus.NOT_FOUND);
        }

        //set created date
        LocalDateTime date = LocalDateTime.now();
        request.setDate(date);


        driverMapper.updateDriverById(driverId, request);
    }

    public String generateDriverId(){
        return "DVR".concat(RandomUtil.randomString(8).toUpperCase());
    }

}
