package com.ph.DriverPH.module.driver.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ph.DriverPH.common.util.DriverIdGenerator;
import com.ph.DriverPH.config.DriverRedisConstant;
import com.ph.DriverPH.exception.ServiceException;
import com.ph.DriverPH.module.driver.entity.Driver;
import com.ph.DriverPH.module.driver.mapstruct.DriverConverter;
import com.ph.DriverPH.module.driver.repository.IDriverRepository;
import com.ph.DriverPH.module.driver.request.DriverRequest;
import com.ph.DriverPH.module.driver.response.DriverResponse;
import com.ph.DriverPH.module.driver.service.IDriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Eejay Taa
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements IDriverService {

    private IDriverRepository iDriverRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final DriverIdGenerator driverIdGenerator;

    @Override
    public void addDriver(DriverRequest request) {
        request.setDate(LocalDateTime.now());
        //Set unique driver id
        request.setDriverId(driverIdGenerator.generateDriverId());
        //Save entity to database
        Driver driver = DriverConverter.INSTANCE.convert(request);
        iDriverRepository.save(driver);
        log.info("---IDriverServiceImpl---addDriver:{}", JSONObject.toJSONString(request));
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
        this.getDriver(driverId);
        //Then remove driver in database and redis.
        Optional<Driver> driver = iDriverRepository.findDriverByDriverId(driverId);
        if(driver.isPresent()) {
            iDriverRepository.delete(driver.get());
            redisTemplate.delete(DriverRedisConstant.DRIVER_REDIS_GET_KEY.concat(driverId));
        }

    }

    @Override
    public void updateDriverById(String driverId, DriverRequest request) {
        Driver findDriver = getDriver(driverId);
//        this.update(new UpdateWrapper<Driver>()
//                .set("first_name", request.getFirstName())
//                .set("last_name", request.getLastName())
//                .set("middle_name", request.getMiddleName())
//                .set("company", request.getCompany())
//                .set("date_updated", LocalDateTime.now())
//                .eq("driver_id", driverId)
//        );

        redisTemplate.opsForValue().set(DriverRedisConstant.DRIVER_REDIS_GET_KEY.concat(driverId), findDriver);
    }

    @Override
    public Page<DriverResponse> getDrivers(Page<Driver> of) {
        return null;
    }

    private Driver getDriver(String id) {
        Optional<Driver> findDriver = iDriverRepository.findDriverByDriverId(id);
        findDriver.orElseThrow(() -> new ServiceException("Driver doesn't exists in our records."));
        return findDriver.get();
    }


}
