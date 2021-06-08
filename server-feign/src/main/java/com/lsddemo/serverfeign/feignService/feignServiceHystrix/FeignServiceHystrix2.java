package com.lsddemo.serverfeign.feignService.feignServiceHystrix;

import com.lsddemo.serverfeign.feignService.FeignServiceHi2;
import org.springframework.stereotype.Component;

@Component
public class FeignServiceHystrix2 implements FeignServiceHi2 {
    @Override
    public String info2() {
        return "info2 ERROR";
    }
}
