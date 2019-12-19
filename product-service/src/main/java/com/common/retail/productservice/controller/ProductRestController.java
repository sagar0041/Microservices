package com.common.retail.productservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/rest/product" , method = RequestMethod.GET)
public class ProductRestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/Client")
    public String welcomeProduct() {
        return "Welcome Client";
    }

    @HystrixCommand(fallbackMethod = "welcomeFallback")
    @GetMapping
    public String welcome() {
        String url ="http://SHOPPINGCART-SERVICE/shopping";
        ServiceInstance serviceInstance=loadBalancerClient.choose(" ");
        System.out.println("Which Port is it connecting to " + "    " + serviceInstance.getUri());
        return restTemplate.getForObject(url,String.class);
    }

    public String welcomeFallback() {
        return "Welcome to Fallback method";
    }
}
