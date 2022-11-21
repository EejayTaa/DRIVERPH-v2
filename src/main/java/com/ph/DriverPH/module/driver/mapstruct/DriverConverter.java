package com.ph.DriverPH.module.driver.mapstruct;

import com.ph.DriverPH.module.driver.entity.Driver;
import com.ph.DriverPH.module.driver.request.DriverRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DriverConverter {

    DriverConverter INSTANCE = Mappers.getMapper(DriverConverter.class);

    /**
     * Convert DriverRequest Class to Driver Class.
     *
     * @param request
     * @return
     */
    Driver convert(DriverRequest request);
}
