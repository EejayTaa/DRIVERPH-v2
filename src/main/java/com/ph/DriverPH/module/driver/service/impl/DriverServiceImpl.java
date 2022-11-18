package com.ph.DriverPH.module.driver.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.ph.DriverPH.config.DriverRedisConstant;
import com.ph.DriverPH.exception.ServiceException;
import com.ph.DriverPH.module.driver.mapper.DriverMapper;
import com.ph.DriverPH.module.driver.request.DriverRequest;
import com.ph.DriverPH.module.driver.response.DriverResponse;
import com.ph.DriverPH.module.driver.service.IDriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Administrator
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements IDriverService {

    private final DriverMapper driverMapper;
    private final RedisTemplate<String, Object> redisTemplate;

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
        String driverId = generateDriverId();
        request.setDriverId(driverId);

        driverMapper.addDriver(request);
        log.info("---IDriverServiceImpl---addDriver:{}", JSONObject.toJSONString(request));
    }

    @Override
    public DriverResponse findDriverById(String driverId) {
        DriverResponse driver = (DriverResponse) redisTemplate.opsForValue().get(DriverRedisConstant.DRIVER_REDIS_GET_KEY.concat(driverId));
        if (ObjectUtil.isNull(driver)) {
            Optional<DriverResponse> findDriver = driverMapper.findDriverById(driverId);
            findDriver.orElseThrow(() -> new ServiceException("Driver doesn't exists."));
            redisTemplate.opsForValue().set(DriverRedisConstant.DRIVER_REDIS_GET_KEY.concat(driverId), findDriver.get());
            return findDriver.get();
        }
        return driver;
    }

    @Override
    public void deleteDriverById(String driverId) {

        //check if driver exists in the database
        DriverResponse driver = findDriverById(driverId);
        if (Objects.isNull(driver)) {
            throw new ServiceException("Driver not found.", HttpStatus.NOT_FOUND);
        }

        driverMapper.deleteDriverById(driverId);
    }

    @Override
    public void updateDriverById(String driverId, DriverRequest request) {

        //check if driver exists in the database
        DriverResponse driver = findDriverById(driverId);
        if (Objects.isNull(driver)) {
            throw new ServiceException("Driver not found.", HttpStatus.NOT_FOUND);
        }

        //set created date
        LocalDateTime date = LocalDateTime.now();
        request.setDate(date);


        driverMapper.updateDriverById(driverId, request);
    }

    public String generateDriverId() {
        return "DVR".concat(RandomUtil.randomString(8).toUpperCase());
    }

}
