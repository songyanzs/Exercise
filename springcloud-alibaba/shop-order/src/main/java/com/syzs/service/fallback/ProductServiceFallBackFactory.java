package com.syzs.service.fallback;

import com.syzs.domain.Product;
import com.syzs.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductServiceFallBackFactory implements FallbackFactory<ProductService>{
    @Override
    public ProductService create(Throwable throwable) {
        log.error("{}",throwable);
        return new ProductService() {
            @Override
            public Product findByPid(Integer pid) {
                Product product = new Product();
                product.setPid(-1);
                return product;
            }
        };
    }
}
