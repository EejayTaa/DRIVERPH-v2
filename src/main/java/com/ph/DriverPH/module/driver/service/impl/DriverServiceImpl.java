package com.ph.DriverPH.module.driver.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ph.DriverPH.common.util.DriverIdGenerator;
import com.ph.DriverPH.config.DriverRedisConstant;
import com.ph.DriverPH.exception.ServiceException;
import com.ph.DriverPH.module.driver.entity.Driver;
import com.ph.DriverPH.module.driver.mapstruct.IDriverConverter;
import com.ph.DriverPH.module.driver.repository.IDriverRepository;
import com.ph.DriverPH.module.driver.request.DriverRequest;
import com.ph.DriverPH.module.driver.response.DriverDetailResponse;
import com.ph.DriverPH.module.driver.service.IDriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements IDriverService {

    private final IDriverRepository iDriverRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final DriverIdGenerator driverIdGenerator;

    @Override
    public void saveDriver(DriverRequest request) {
        //Set unique driver id
        request.setDriverId(driverIdGenerator.generateDriverId());
        //Save entity to database
        Driver driver = IDriverConverter.INSTANCE.convert(request);
        log.info("=====IDriverServiceImpl=====saveDriver:{}=====", request);
        iDriverRepository.save(driver);
    }

    @Override
    public DriverDetailResponse findDriverById(String driverId) {
        Driver driver = (Driver) redisTemplate.opsForValue().get(DriverRedisConstant.DRIVER_REDIS_GET_KEY.concat(driverId));
        if (ObjectUtil.isNull(driver)) {
            Driver findDriver = this.getDriver(driverId);
            redisTemplate.opsForValue().set(DriverRedisConstant.DRIVER_REDIS_GET_KEY.concat(driverId), findDriver);
            return IDriverConverter.INSTANCE.convert(findDriver);
        }
        return IDriverConverter.INSTANCE.convert(driver);
    }

    @Override
    public void deleteDriverById(String driverId) {
        //Check if user exists, if so then remove driver in database and redis.
        iDriverRepository.delete(this.getDriver(driverId));
        redisTemplate.delete(DriverRedisConstant.DRIVER_REDIS_GET_KEY.concat(driverId));
    }

    @Override
    public void updateDriverById(String driverId, DriverRequest request) {
        Driver driver = this.getDriver(driverId);
        redisTemplate.opsForValue().set(DriverRedisConstant.DRIVER_REDIS_GET_KEY.concat(driverId), driver);
    }

    @Override
    public List<DriverDetailResponse> findAllDrivers(Integer page, Integer size) {
        List<DriverDetailResponse> response = new ArrayList<>();
        Page<Driver> drivers = iDriverRepository.findAll(PageRequest.of(page, size));
        drivers.getContent().forEach(driver -> {
            response.add(IDriverConverter.INSTANCE.convert(driver));
        });

        return response;
    }

    private Driver getDriver(String id) {
        Optional<Driver> driver = iDriverRepository.findDriverByDriverId(id);
        driver.orElseThrow(() -> new ServiceException("Driver not found."));
        log.info("=====DriverServiceImpl=====getDriver:{}=====", driver);
        return driver.get();
    }
}
