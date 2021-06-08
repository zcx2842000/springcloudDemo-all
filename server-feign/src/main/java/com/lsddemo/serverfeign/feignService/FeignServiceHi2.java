package com.lsddemo.serverfeign.feignService;

import com.lsddemo.serverfeign.feignService.feignServiceHystrix.FeignServiceHystrix;
import com.lsddemo.serverfeign.feignService.feignServiceHystrix.FeignServiceHystrix2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-miya",fallback = FeignServiceHystrix2.class)
@Service
public interface FeignServiceHi2 {
    @RequestMapping(value = "/info2")
    public String info2();
}
