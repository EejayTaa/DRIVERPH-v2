package com.ph.DriverPH.module.driver.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ph.DriverPH.common.ResponseHandler;
import com.ph.DriverPH.module.driver.entity.Driver;
import com.ph.DriverPH.module.driver.request.DriverRequest;
import com.ph.DriverPH.module.driver.response.DriverResponse;
import com.ph.DriverPH.module.driver.service.IDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * Driver Controller
 *
 * @author Eejay Taa
 */
@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {

    private final IDriverService iDriverService;

    @GetMapping("/getDrivers")
    public ResponseEntity getDrivers(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<DriverResponse> response = iDriverService.getDrivers(Page.of(page, size));
        return ResponseHandler.OK(response);
    }

    @PostMapping("/addDriver")
    public ResponseEntity addDrivers(@RequestBody @Validated DriverRequest request) {
        iDriverService.addDriver(request);
        return ResponseHandler.CREATED();
    }

    @GetMapping("/getDriverById/{driverId}")
    public ResponseEntity findDriverById(@PathVariable String driverId) {
        //Check if driver id is null
        if (StringUtils.isEmpty(driverId)) {
            return ResponseHandler.BAD_REQUEST(driverId, "Driver ID is a required field.");
        }
        Driver driver = iDriverService.findDriverById(driverId);
        return ResponseHandler.OK(driver);
    }

    @DeleteMapping("/deleteDriverById/{driverId}")
    public ResponseEntity deleteDriverById(@PathVariable String driverId) {

        //check if driver id is null
        if (StringUtils.isEmpty(driverId)) {
            return ResponseHandler.BAD_REQUEST(driverId, "Driver ID is a required field.");
        }
        iDriverService.deleteDriverById(driverId);
        return ResponseHandler.OK();
    }

    @PutMapping("/updateDriverById/{driverId}")
    public ResponseEntity updateDriverById(@PathVariable String driverId, @RequestBody @Validated DriverRequest request) {
        //check if driver id is null
        if (StringUtils.isEmpty(driverId)) {
            return ResponseHandler.BAD_REQUEST(driverId, "Driver ID is a required field.");
        }
        iDriverService.updateDriverById(driverId, request);
        return ResponseHandler.OK();
    }
}
