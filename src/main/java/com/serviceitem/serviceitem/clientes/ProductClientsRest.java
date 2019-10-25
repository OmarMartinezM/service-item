package com.serviceitem.serviceitem.clientes;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.serviceitem.serviceitem.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-product")
public interface ProductClientsRest {

    @GetMapping("/listed") //With feing, the getmapping is the url where is going to connect to the other MS
    public List<Product> listed();

    @GetMapping("listed/{id}")
    public Product detail(@PathVariable Long id);

}
