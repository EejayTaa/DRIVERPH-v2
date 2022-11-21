package com.ph.DriverPH.module.driver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ph.DriverPH.module.driver.entity.Driver;
import com.ph.DriverPH.module.driver.response.DriverResponse;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author Eejay Ta-a
 */
@Mapper
public interface DriverMapper extends BaseMapper<Driver> {


    Page<DriverResponse> getDrivers(Page<Driver> of);
}
