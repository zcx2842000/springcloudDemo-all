package com.lsddemo.scfgatewayfirstsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class ScFGatewayFirstSightApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScFGatewayFirstSightApplication.class, args);
    }

    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder builder) {
        String httpUri = "http://httpbin.org:80";
        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f -> f.addRequestHeader("hello", "world"))
                        .uri(httpUri))
                .route(p -> p.host("*.hystrix.com")
                        .filters(f -> f.hystrix(config -> config
                                .setName("mydemo")
                                .setFallbackUri("forward:/error")))
                        .uri(httpUri)).build();
    }


    @RequestMapping("/error")
    public Mono<String> error() {
        return Mono.just("hystrix -> error ");
    }


}
