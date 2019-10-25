package com.serviceitem.serviceitem.clientes;

import com.serviceitem.serviceitem.models.EventDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("event-brite-consulting")
public interface EventClientRest {

    @GetMapping("/events/{keyWord}")
    public List<EventDTO> getFullEvents(@PathVariable("keyWord") String keyWord);

    @GetMapping("/prueba")
    public String prueba();
}
