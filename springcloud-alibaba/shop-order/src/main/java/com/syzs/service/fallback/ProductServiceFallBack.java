package com.syzs.service.fallback;

import com.syzs.domain.Product;
import com.syzs.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

//容错类要求必须实现被容错的接口,并为每个方法实现容错方案
//一旦Fegin远程调用出现问题了，就会进入当前类中的同名方法，执行容错逻辑
@Component
@Slf4j
public class ProductServiceFallBack implements ProductService{
    @Override
    public Product findByPid(Integer pid) {
        Product product = new Product();
        product.setPid(-1);
        return product;
    }
}
