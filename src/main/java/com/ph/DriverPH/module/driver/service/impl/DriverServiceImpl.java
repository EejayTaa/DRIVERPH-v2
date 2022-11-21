package com.ph.DriverPH.module.driver.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ph.DriverPH.common.util.DriverIdGenerator;
import com.ph.DriverPH.config.DriverRedisConstant;
import com.ph.DriverPH.exception.ServiceException;
import com.ph.DriverPH.module.driver.entity.Driver;
import com.ph.DriverPH.module.driver.mapper.DriverMapper;
import com.ph.DriverPH.module.driver.mapstruct.DriverConverter;
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
public class DriverServiceImpl extends ServiceImpl<DriverMapper, Driver> implements IDriverService {

    private final DriverMapper driverMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final DriverIdGenerator driverIdGenerator;

    @Override
    public void addDriver(DriverRequest request) {
        request.setDate(LocalDateTime.now());
        //Set unique driver id
        request.setDriverId(driverIdGenerator.generateDriverId());
        //Save entity to database
        Driver driver = DriverConverter.INSTANCE.convert(request);
        this.save(driver);
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
        this.remove(new QueryWrapper<Driver>()
                .lambda()
                .eq(Driver::getDriverId, driverId)
        );
        redisTemplate.delete(DriverRedisConstant.DRIVER_REDIS_GET_KEY.concat(driverId));
    }

    @Override
    public void updateDriverById(String driverId, DriverRequest request) {
        Driver findDriver = getDriver(driverId);
        this.update(new UpdateWrapper<Driver>()
                .set("first_name", request.getFirstName())
                .set("last_name", request.getLastName())
                .set("middle_name", request.getMiddleName())
                .set("company", request.getCompany())
                .set("date_updated", LocalDateTime.now())
                .eq("driver_id", driverId)
        );

        redisTemplate.opsForValue().set(DriverRedisConstant.DRIVER_REDIS_GET_KEY.concat(driverId), findDriver);
    }

    @Override
    public Page<DriverResponse> getDrivers(Page<Driver> of) {
        return driverMapper.getDrivers(of);
    }

    private Driver getDriver(String id) {
        Optional<Driver> findDriver = Optional.ofNullable(Optional.ofNullable(this.getOne(new QueryWrapper<Driver>()
                .lambda()
                .eq(Driver::getDriverId, id)
        )).orElseThrow(() -> new ServiceException("Driver doesn't exists in our records.")));
        return findDriver.get();
    }


}
