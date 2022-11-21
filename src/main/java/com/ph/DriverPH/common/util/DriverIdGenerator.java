package com.ph.DriverPH.common.util;

import cn.hutool.core.util.RandomUtil;
import org.springframework.stereotype.Service;


@Service
public class DriverIdGenerator {

    public  String generateDriverId() {
        return "DVR".concat(RandomUtil.randomString(8).toUpperCase());
    }
}
