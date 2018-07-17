package com.example.demo.service;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.example.demo.util.CsvReaderUtils;

@Service("connectedCities")
public class CitiesServiceImpl implements CitiesService{

	@Value("${csvpath}")
	private String csvFileName;
	
	private static ConcurrentHashMap<String, String> connectedCities=new ConcurrentHashMap<>();
	
	private ResourceLoader resourceLoader;

    @Autowired
    public CitiesServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

	
	public CitiesServiceImpl() {}
	
	@PostConstruct
	public void init() throws Exception{
		org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:"+csvFileName);
		CsvReaderUtils.loadCsv(resource.getFile(),connectedCities);
	}
	
/*	public ConcurrentHashMap<String, String> getCities(){
		return connectedCities;
	}
*/
	@Override
	public ConcurrentHashMap<String, String> getConnectedCities() {
		// TODO Auto-generated method stub
		return connectedCities;
	}
}
