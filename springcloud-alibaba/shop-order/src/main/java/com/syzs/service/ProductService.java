package com.syzs.service;


import com.syzs.domain.Product;
import com.syzs.service.fallback.ProductServiceFallBack;
import com.syzs.service.fallback.ProductServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product",fallbackFactory = ProductServiceFallBackFactory.class)
public interface ProductService {


    //@FeignClient+@GetMapping 就是一个完整的请求路径 http://service- product/product/{pid}
    @GetMapping(value = "/product/{pid}")
    Product findByPid(@PathVariable("pid") Integer pid);
}
