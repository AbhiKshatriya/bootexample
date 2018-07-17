package com.example.demo.controller;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CitiesService;

@RestController
@RequestMapping("/connected")
public class CitiesRestController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CitiesRestController.class);
	
	@Autowired
	CitiesService citiesService;
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> fetchConnectedCities(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
        LOGGER.info("cities : "+origin+"="+destination);
        ConcurrentHashMap<String, String> connectedCities = citiesService.getConnectedCities();
        if (connectedCities == null) {
            LOGGER.error("Cities not found.");
            return new ResponseEntity<String>("Cities Not available", HttpStatus.NOT_FOUND);
        }
        if(StringUtils.isEmpty(origin) && StringUtils.isEmpty(destination)) {
        	if(connectedCities.get(origin.toUpperCase()).equalsIgnoreCase(destination.toUpperCase())) {
        		return new ResponseEntity<String>("Yes", HttpStatus.OK);
        	}else if(connectedCities.get(destination.toUpperCase()).equalsIgnoreCase(origin.toUpperCase())){
        		return new ResponseEntity<String>("Yes", HttpStatus.OK);
        	}else {
        		return new ResponseEntity<String>("No", HttpStatus.OK);
        	}
        }
        return new ResponseEntity<String>("No", HttpStatus.OK);
    }
}
