package com.serviceitem.serviceitem.models.service;

import com.serviceitem.serviceitem.models.EventDTO;
import com.serviceitem.serviceitem.models.Item;

import java.util.List;

public interface ItemService {

    public List<Item> findAll();
    public Item findById(Long id, Integer cantidad);
    public List<EventDTO> getEvents(String keyWord);
    public String prueba();
}
