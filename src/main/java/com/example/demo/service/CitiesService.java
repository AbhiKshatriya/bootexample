package com.example.demo.service;

import java.util.concurrent.ConcurrentHashMap;

public interface CitiesService {

	ConcurrentHashMap<String, String> getConnectedCities();
}
