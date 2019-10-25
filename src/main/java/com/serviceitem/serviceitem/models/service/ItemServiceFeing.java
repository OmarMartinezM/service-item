package com.serviceitem.serviceitem.models.service;

import com.serviceitem.serviceitem.clientes.EventClientRest;
import com.serviceitem.serviceitem.clientes.ProductClientsRest;
import com.serviceitem.serviceitem.models.EventDTO;
import com.serviceitem.serviceitem.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class ItemServiceFeing implements ItemService {

    @Autowired
    private ProductClientsRest clientFeign;

    @Autowired
    private EventClientRest eventClientRest;

    @Override
    public List<Item> findAll() {
        return clientFeign.listed().stream()
                .map(p -> new Item(p,1))
                .collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        return new  Item(clientFeign.detail(id), quantity);
    }

    @Override
    public List<EventDTO> getEvents(String keyWord) {
        return eventClientRest.getFullEvents(keyWord);
    }

    @Override
    public String prueba() {
        return eventClientRest.prueba();
    }


}
