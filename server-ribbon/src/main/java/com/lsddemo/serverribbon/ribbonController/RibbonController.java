package com.lsddemo.serverribbon.ribbonController;

import com.lsddemo.serverribbon.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {
    @Autowired
    RibbonService ribbonService;

    @RequestMapping("hi")
    public String hi(@RequestParam(value = "name",defaultValue = "luoshudong")String name){
        return ribbonService.hiString(name);
    }

}
