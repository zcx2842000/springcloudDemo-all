package com.lsddemo.serverfeign.feignService.feignServiceHystrix;

import com.lsddemo.serverfeign.feignService.FeignService;
import org.springframework.stereotype.Component;

@Component
public class FeignServiceHystrix implements FeignService {
    @Override
    public String sayHiFeignOne(String name) {
        return "sorry,"+name+",error!,from one";
    }

    @Override
    public String sayHiFeignTwo(String name) {
        return  "sorry,"+name+",error!,from two";
    }
}
