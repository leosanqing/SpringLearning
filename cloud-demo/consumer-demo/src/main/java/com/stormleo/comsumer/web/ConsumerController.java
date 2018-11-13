package com.stormleo.comsumer.web;

import com.stormleo.comsumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer")
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;



    @GetMapping("{id}")
    public User queryUserById(@PathVariable("id") Long id){
        List<ServiceInstance> list=discoveryClient.getInstances("user-service");
        ServiceInstance instance=list.get(0);


        String url="http://"+instance.getHost()+":"+instance.getPort()+"/user/"+id;
        System.out.println(instance.getHost()+instance.getPort());
        User user=restTemplate.getForObject(url,User.class);
        return user;
    }
}
