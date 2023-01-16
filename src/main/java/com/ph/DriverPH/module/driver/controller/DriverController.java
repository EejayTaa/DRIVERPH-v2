package com.ph.DriverPH.module.driver.controller;

import com.ph.DriverPH.common.ResponseHandler;
import com.ph.DriverPH.module.driver.entity.Driver;
import com.ph.DriverPH.module.driver.request.DriverRequest;
import com.ph.DriverPH.module.driver.service.IDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DriverController {

    private final IDriverService iDriverService;

    @GetMapping("/drivers")
    public ResponseEntity findAllDrivers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return ResponseHandler.OK(iDriverService.findAllDrivers(page, size));
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity findDriverById(@PathVariable String id) {
        return ResponseHandler.OK(iDriverService.findDriverById(id));
    }

    @PostMapping("/driver")
    public ResponseEntity saveDriver(@RequestBody @Validated DriverRequest request) {
        iDriverService.saveDriver(request);
        return ResponseHandler.CREATED();
    }

    @DeleteMapping("/driver/{id}")
    public ResponseEntity deleteDriverById(@PathVariable String id) {
        iDriverService.deleteDriverById(id);
        return ResponseHandler.OK();
    }

    @PutMapping("/driver/{id}")
    public ResponseEntity updateDriverById(@PathVariable String id, @RequestBody @Validated DriverRequest request) {
        iDriverService.updateDriverById(id, request);
        return ResponseHandler.OK();
    }
}
