package com.serviceitem.serviceitem.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.serviceitem.serviceitem.models.EventDTO;
import com.serviceitem.serviceitem.models.Item;
import com.serviceitem.serviceitem.models.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private Environment env;

    @Value("${configuration.text}")
    private String text;

    @GetMapping("/listed")
    public List<Item> listed(){
        return itemService.findAll();
    }

    @GetMapping("/listed/{id}/{quantity}")
    public Item detail(@PathVariable Long id, @PathVariable Integer quantity){
        return itemService.findById(id, quantity);
    }

    @HystrixCommand(fallbackMethod = "alternativeMethod")
    @GetMapping("/events/{keyWord}")
    public List<EventDTO> getFullEvents(@PathVariable("keyWord") String keyWord){
        return itemService.getEvents(keyWord);
    }

    @GetMapping("/prueba")
    public String prueba(){
        return itemService.prueba();
    }

    @GetMapping("/get-config")
    public ResponseEntity<?>  getConfiguration(@Value("${spring.application.name}") String port){
        Map<String, String> json = new HashMap<>();
        json.put("text",text);
        json.put("port",port);

        if(env.getActiveProfiles().length > 0 &&  env.getActiveProfiles()[0].equals("dev")){
            json.put("author.name", env.getProperty("configuration.author.name"));
            json.put("author.email", env.getProperty("configuration.author.email"));
        }
        return new ResponseEntity<Map<String,String>>(json, HttpStatus.OK);
    }


    public List<EventDTO> alternativeMethod(String keyWord){
        List<EventDTO> eventList = new ArrayList();
        return eventList;
    }
}
