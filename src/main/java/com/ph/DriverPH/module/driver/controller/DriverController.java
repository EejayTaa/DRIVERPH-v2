package com.ph.DriverPH.module.driver.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ph.DriverPH.common.ResponseHandler;
import com.ph.DriverPH.exception.ServiceException;
import com.ph.DriverPH.module.driver.request.DriverRequest;
import com.ph.DriverPH.module.driver.response.DriverResponse;
import com.ph.DriverPH.module.driver.service.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Driver Controller
 *
 */
@RestController
@RequestMapping("/driver")
public class DriverController {


    @Autowired
    IDriverService iDriverService;

    @GetMapping("/getDrivers")
    public ResponseEntity getDrivers(){
        List<DriverResponse> drivers = iDriverService.getDrivers();
        return ResponseHandler.responseBuilder(drivers , HttpStatus.OK);
    }

    @PostMapping("/addDriver")
    public ResponseEntity addDrivers(@RequestBody @Validated DriverRequest request){

        iDriverService.addDriver(request);

        return ResponseHandler.responseBuilder(HttpStatus.CREATED);
    }

    @GetMapping("/getDriverById/{driverId}")
    public ResponseEntity findDriverById(@PathVariable String driverId){
        //Check if driver id is null
        if(StringUtils.isEmpty(driverId)){
            throw new ServiceException("Driver id is a required field.", HttpStatus.BAD_REQUEST);
        }
        DriverResponse driver = iDriverService.findDriverById(driverId);
        return ResponseHandler.responseBuilder("Success", HttpStatus.OK, driver);
    }

    @DeleteMapping("/deleteDriverById/{driverId}")
    public ResponseEntity deleteDriverById(@PathVariable String driverId){

        //check if driver id is null
        if(StringUtils.isEmpty(driverId)){
            throw new ServiceException("Driver id is a required field.", HttpStatus.BAD_REQUEST);
        }

        iDriverService.deleteDriverById(driverId);

        return ResponseHandler.responseBuilder(HttpStatus.OK);
    }

    @PutMapping("/updateDriverById/{driverId}")
    public ResponseEntity updateDriverById(@PathVariable String driverId, @RequestBody @Validated DriverRequest request){

        //check if driver id is null
        if(StringUtils.isEmpty(driverId)){
            throw new ServiceException("Driver id is a required field.", HttpStatus.BAD_REQUEST);
        }

        iDriverService.updateDriverById(driverId, request);

        return ResponseHandler.responseBuilder(HttpStatus.OK);
    }
}
