package com.ph.DriverPH.module.driver.mapstruct;

import com.ph.DriverPH.module.driver.entity.Driver;
import com.ph.DriverPH.module.driver.request.DriverRequest;
import com.ph.DriverPH.module.driver.response.DriverDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IDriverConverter {

    IDriverConverter INSTANCE = Mappers.getMapper(IDriverConverter.class);

    /**
     * Convert DriverRequest Class to Driver Class.
     *
     * @param request
     * @return
     */
    Driver convert(DriverRequest request);

    DriverDetailResponse convert(Driver driver);
}
