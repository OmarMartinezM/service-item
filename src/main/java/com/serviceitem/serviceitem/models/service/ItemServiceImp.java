package com.serviceitem.serviceitem.models.service;

import com.serviceitem.serviceitem.models.EventDTO;
import com.serviceitem.serviceitem.models.Item;
import com.serviceitem.serviceitem.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceImp implements ItemService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Item> findAll() {
        List<Product> products = Arrays.asList(restTemplate.getForObject("http://service-product/listed",Product[].class));
        return products.stream()
                .map(p -> new Item(p,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        pathVariables.put("quantity", quantity.toString());
        Product product = restTemplate.getForObject("http://service-product/listed/{id}",Product.class, pathVariables);
        return new Item(product,quantity);
    }

    @Override
    public List<EventDTO> getEvents(String keyWord) {
        return null;
    }

    @Override
    public String prueba() {
        return null;
    }
}
