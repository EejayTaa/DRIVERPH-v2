package com.ph.DriverPH.module.driver.repository;

import com.ph.DriverPH.module.driver.entity.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IDriverRepository extends CrudRepository<Driver, Long>, PagingAndSortingRepository<Driver, Long> {

    Optional<Driver> findDriverByDriverId(String driverId);
}
