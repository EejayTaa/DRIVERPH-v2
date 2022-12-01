package com.ph.DriverPH.module.driver.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.ph.DriverPH.common.util.DriverIdGenerator;
import com.ph.DriverPH.config.DriverRedisConstant;
import com.ph.DriverPH.exception.ServiceException;
import com.ph.DriverPH.module.driver.entity.Driver;
import com.ph.DriverPH.module.driver.mapstruct.DriverConverter;
import com.ph.DriverPH.module.driver.repository.IDriverRepository;
import com.ph.DriverPH.module.driver.request.DriverRequest;
import com.ph.DriverPH.module.driver.service.IDriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Eejay Taa
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements IDriverService {

    private final IDriverRepository iDriverRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final DriverIdGenerator driverIdGenerator;

    @Override
    public void addDriver(DriverRequest request) {
        request.setDate(LocalDateTime.now());
        //Set unique driver id
        request.setDriverId(driverIdGenerator.generateDriverId());
        //Save entity to database
        Driver driver = DriverConverter.INSTANCE.convert(request);
        log.info("---IDriverServiceImpl---addDriver:{}", JSONObject.toJSONString(request));
        iDriverRepository.save(driver);
    }

    @Override
    public Driver findDriverById(String driverId) {
        Driver driver = (Driver) redisTemplate.opsForValue().get(DriverRedisConstant.DRIVER_REDIS_GET_KEY.concat(driverId));
        if (ObjectUtil.isNull(driver)) {
            Driver findDriver = getDriver(driverId);
            redisTemplate.opsForValue().set(DriverRedisConstant.DRIVER_REDIS_GET_KEY.concat(driverId), findDriver);
            return findDriver;
        }
        return driver;
    }

    @Override
    public void deleteDriverById(String driverId) {
        //Check if driver exists in the database
        Driver driver = this.getDriver(driverId);
        //Then remove driver in database and redis.
        iDriverRepository.delete(driver);
        redisTemplate.delete(DriverRedisConstant.DRIVER_REDIS_GET_KEY.concat(driverId));
    }

    @Override
    public void updateDriverById(String driverId, DriverRequest request) {
        Driver driver = getDriver(driverId);
        redisTemplate.opsForValue().set(DriverRedisConstant.DRIVER_REDIS_GET_KEY.concat(driverId), driver);
    }

    @Override
    public List<Driver> getDrivers(PageRequest of) {
        return iDriverRepository.findAll(of).getContent();
    }


    private Driver getDriver(String id) {
        Optional<Driver> driver = iDriverRepository.findDriverByDriverId(id);
        driver.orElseThrow(() -> new ServiceException("Driver not found."));
        return driver.get();
    }


}
