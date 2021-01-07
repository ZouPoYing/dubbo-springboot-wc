package com.tom.controller;

import com.tom.IProductService;
import com.tom.Product;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanghongjun19
 * @date 2021/01/07
 **/

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    public IProductService productService;

    @RequestMapping("/get")
    public Product get(Long id) {
        Product product = productService.get(id);
        log.info("请求结果是:" + product);
        return product;
    }

    @RequestMapping("/save")
    public String save() {
        productService.save(new Product());
        return "success";
    }
}
