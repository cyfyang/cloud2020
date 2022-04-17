package com.lucifer.springcloud.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    public final static String ATTRIBUTE_IGNORE_TEST_GLOBAL_FILTER = "@ignoreTestGlobalFilter";
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        if(exchange.getAttribute(ATTRIBUTE_IGNORE_TEST_GLOBAL_FILTER)!=null){
//            return chain.filter(exchange);
//        }else{
//            String token = exchange.getRequest().getQueryParams().getFirst("token");
//            if(StringUtils.isBlank(token)){
//                ServerHttpResponse response = exchange.getResponse();
//
//                Map<String,Object> responseData = Maps.newHashMap();
//                responseData.put("code", HttpStatus.UNAUTHORIZED.value());
//                responseData.put("message","非法请求");
//                responseData.put("cause","Token is null");
//
//                try {
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    byte[] bytes = objectMapper.writeValueAsBytes(responseData);
//                    //输出错误信息到页面
//                    DataBuffer buffer = response.bufferFactory().wrap(bytes);
//
//                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                    response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
//                    return response.writeWith(Mono.just(buffer));
//
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return chain.filter(exchange);
//    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("************come in MyLogGateWayFilter:  " + new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if(uname == null){
            log.info("*********用户名为null，非法用户，o(T_T)o");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }
}
