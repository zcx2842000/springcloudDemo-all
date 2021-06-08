package com.lsddemo.servicehi;

import brave.sampler.Sampler;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;


@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class ServiceHiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHiApplication.class, args);
    }

    private  static  final Logger log = Logger.getLogger(ServiceHiApplication.class.getName());

    @Value("${server.port}")
    String port;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public  RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("hi")
    public String hi(@RequestParam(name = "name",defaultValue = "luoshudong") String name){
        log.info("hi，i am service-hi,param name  is : "+name+"form : " + port);
        return restTemplate.getForObject("http://localhost:8770/info?name="+name,String.class);

    }

    @Bean
    public Sampler defaultSampler(){
        return  Sampler.ALWAYS_SAMPLE;
    }

    @RequestMapping("info")
    @HystrixCommand(fallbackMethod = "isError")
    public String info(@RequestParam(value = "name",defaultValue = "luoshudong") String name){
        return "hi,"+ name +",i am from port:"+ port;
    }

    public String isError(String name){
        return "sorry, "+name+"error, from port: "+port;
    }


    @RequestMapping("hi2")
    public String hi2(){
        log.info("hi ,i am hi2 ,from : "+port);
        return "hi ,i am hi2 ,from : " +port;
    }


    /**
     * 链路追踪入口
     * @return
     */
    @RequestMapping("info2")
    public String info2(){
        log.log(Level.INFO,"calling trace service-hi");
        return "i'm service-hi";
    }

}
