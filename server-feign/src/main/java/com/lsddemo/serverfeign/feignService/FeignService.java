package com.lsddemo.serverfeign.feignService;

import com.lsddemo.serverfeign.feignService.feignServiceHystrix.FeignServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-hi",fallback = FeignServiceHystrix.class)
@Service
public interface FeignService {
     @RequestMapping(value = "/hi",method = RequestMethod.GET)
     String sayHiFeignOne(@RequestParam(value = "name")  String name);


     @RequestMapping(value = "/hi2",method = RequestMethod.GET)
     String sayHiFeignTwo(@RequestParam(value = "name")  String name);
}
