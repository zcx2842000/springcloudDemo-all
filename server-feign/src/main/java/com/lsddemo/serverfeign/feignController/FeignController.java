package com.lsddemo.serverfeign.feignController;

import com.lsddemo.serverfeign.feignService.FeignService;
import com.lsddemo.serverfeign.feignService.FeignServiceHi2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
    @Autowired
    FeignService feignService;

    @Autowired
    FeignServiceHi2 feignServiceHi2;

    @RequestMapping("hi")
    public String hi(@RequestParam(value = "name",defaultValue = "luoshudong") String name){
        return feignService.sayHiFeignOne(name);
    }

    @RequestMapping("hi2")
    public String hi2(@RequestParam(value = "name",defaultValue = "luoshudong") String name){
        return feignService.sayHiFeignTwo(name);
    }

    @RequestMapping("info2")
    public String info2(){
        return  feignServiceHi2.info2();
    }
}
