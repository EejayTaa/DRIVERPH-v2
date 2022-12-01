package com.ph.DriverPH.module.driver.repository;

import com.ph.DriverPH.module.driver.entity.Driver;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IDriverRepository extends CrudRepository<Driver, Long> {

    Optional<Driver> findDriverByDriverId(String driverId);
}
