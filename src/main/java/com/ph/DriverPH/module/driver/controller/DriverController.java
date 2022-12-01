package com.ph.DriverPH.module.driver.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ph.DriverPH.common.ResponseHandler;
import com.ph.DriverPH.module.driver.entity.Driver;
import com.ph.DriverPH.module.driver.request.DriverRequest;
import com.ph.DriverPH.module.driver.response.DriverResponse;
import com.ph.DriverPH.module.driver.service.IDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Driver Controller
 *
 * @author Eejay Taa
 */
@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final IDriverService iDriverService;

    @GetMapping("/")
    public ResponseEntity getDrivers( @RequestParam(defaultValue = "1") Integer size, @RequestParam(defaultValue = "0") Integer page) {
        List<Driver> list = iDriverService.getDrivers(PageRequest.of(page, size));
        return ResponseHandler.OK(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity getDriverById(@PathVariable String id) {
        Driver driver = iDriverService.findDriverById(id);
        return ResponseHandler.OK(driver);
    }

    @PostMapping("/")
    public ResponseEntity addDrivers(@RequestBody @Validated DriverRequest request) {
        iDriverService.addDriver(request);
        return ResponseHandler.CREATED();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDriverById(@PathVariable String id) {
        iDriverService.deleteDriverById(id);
        return ResponseHandler.OK();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateDriverById(@PathVariable String id, @RequestBody @Validated DriverRequest request) {
        iDriverService.updateDriverById(id, request);
        return ResponseHandler.OK();
    }
}
