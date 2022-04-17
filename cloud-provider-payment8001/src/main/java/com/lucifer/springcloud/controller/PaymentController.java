package com.lucifer.springcloud.controller;

import com.lucifer.springcloud.entities.CommonResult;
import com.lucifer.springcloud.entities.Payment;
import com.lucifer.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("save")
    public CommonResult save(@RequestBody Payment payment){
        int result = paymentService.save(payment);
        if(result>0){
            return new CommonResult(200, "保存成功,serverPort:"+serverPort,result);
        }else{
            return new CommonResult(444,"保存失败",null);
        }
    }

    @GetMapping("get/{id}")
    public CommonResult getPaymentById(@PathVariable("id")Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("nihao a :"+payment+"哈哈哈  的哈萨克多久啊是");
        if(payment != null){
            return new CommonResult(200, "查询成功,serverPort:"+serverPort,payment);
        }else{
            return new CommonResult(444,"未查到记录，查询ID："+id,null);
        }
    }


    /**
     * 列出所有的注册的服务，并打印服务信息
     */
    @GetMapping("discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        services.stream().forEach(item ->{
            log.info(item);
        });

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.stream().forEach(inst->{
            log.info(inst.getServiceId()+"\t"+inst.getHost()+"\t"+inst.getPort()+"\t"+inst.getUri());
        });

        return this.discoveryClient;
    }

    @GetMapping("lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping("feign/timeout")
        public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
