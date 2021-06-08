package com.lsddemo.servicemiya;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class ServiceMiyaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMiyaApplication.class, args);
    }


    @Value("${server.port}")
    String port;

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Autowired
    RestTemplate restTemplate;

    private  static  final  Logger log = Logger.getLogger(ServiceMiyaApplication.class.getName());
    @RequestMapping("hi")
    public String hi(@RequestParam(name = "name",defaultValue = "luoshudong") String name){
        return "hi,i am "+name+",the port is from :" + port;
    }

    @RequestMapping("info")
    public String info(@RequestParam(name = "name",defaultValue = "luoshudong") String name){
        log.info("i am miya  and is called and param name is "+name);
        return  restTemplate.getForObject("http://localhost:8763/info?name="+name,String.class);
    }


    @RequestMapping("info2")
    public String info2(){
        log.info("i am miya  and is called and method name is info2");
        return  restTemplate.getForObject("http://localhost:8763/hi2",String.class);
    }
    @Bean
    public Sampler sampler(){
        return Sampler.ALWAYS_SAMPLE;
    }


    @RequestMapping("info3")
    public String info3(){
       log.log(Level.INFO,"calling trace service-miya");
        return "hi,i'm service-miya";
    }
}
