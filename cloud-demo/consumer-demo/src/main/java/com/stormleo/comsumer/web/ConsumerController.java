package com.stormleo.comsumer.web;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stormleo.comsumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer")
@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    //@Autowired
    //private DiscoveryClient discoveryClient;

    //@Autowired
  //  private RibbonLoadBalancerClient client;


//    @GetMapping("{id}")
//    public User queryUserById(@PathVariable("id") Long id){
//
//        //获取实例对象
//       // List<ServiceInstance> list=discoveryClient.getInstances("user-service");
//       // ServiceInstance instance=list.get(0);
//
//        //轮询
//        //ServiceInstance instance=client.choose("user-service");
//
//
//        //String url="http://"+instance.getHost()+":"+instance.getPort()+"/user/"+id;
//       // System.out.println(instance.getHost()+instance.getPort());
//        String url="http://user-service/user/"+id;
//        User user=restTemplate.getForObject(url,User.class);
//        System.out.println(url);
//        return user;
//    }
    //@HystrixCommand(fallbackMethod = "queryUserByIdFallback")
    @HystrixCommand(commandProperties ={
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "100000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50")
    })
    @GetMapping("{id}")
    public String queryUserById(@PathVariable("id") Long id){
        if(id%2==0){
            throw new RuntimeException("");
        }

        String url="http://user-service/user/"+id;
        String user=restTemplate.getForObject(url,String.class);
        System.out.println(url);
        return user;
    }
    public String queryUserByIdFallback( Long id){

        return "页面太拥挤！";
    }
    public String defaultFallback(){

        return "页面太拥挤！";
    }
}
