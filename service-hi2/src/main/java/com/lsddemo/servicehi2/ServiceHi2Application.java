package com.lsddemo.servicehi2;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableHystrix
@EnableCircuitBreaker
@RestController
public class ServiceHi2Application {
    private  static  final Logger log = Logger.getLogger(ServiceHi2Application.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(ServiceHi2Application.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("hi")
    @HystrixCommand(fallbackMethod = "isError")
    public String hi(@RequestParam(value = "name",defaultValue = "luoshudong") String name){
        return "hi,"+name+",i am hi2,the port is from "  + port;
    }

    public String isError(@RequestParam(value = "name",defaultValue = "luoshudong") String name){
        return "sorry,"+name+", i am error ,thr port is from "+port;
    }

    @RequestMapping("hi2")
    public String hi2(){
        log.info("hi ,i am hi2 ,from : "+port +"to miya : port 8772");
        return "hi ,i am hi2 ,from : " +port;
    }
}
