package com.example.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import com.opencsv.CSVReader;

@Component
public class CsvReaderUtils {

	CsvReaderUtils(){}
	
	public static void loadCsv(File file, ConcurrentHashMap<String , String> mapCities) {
		
		try (
	            Reader reader =  new InputStreamReader(new FileInputStream(file));
	            CSVReader csvReader = new CSVReader(reader);
	        ) {
	            // Reading Records One by One in a String array
				String[] city;
	            while ((city = csvReader.readNext()) != null) {
	            	mapCities.put(city[0].toUpperCase(), city[1].toUpperCase());
	           }
	            
/*	            List<Map.Entry<String, String>> entries=new LinkedList<>(mapCities.entrySet());
	    		ConcurrentHashMap<String, List<String>> finalMap=new ConcurrentHashMap<>();
	    		
	    		for(Entry<String, String> iter: entries)
	    		{
	    			List<String> cities=new ArrayList<>();
	    			if(!finalMap.containsKey(iter.getKey())) {
	    				finalMap.put(iter.getKey(), new ArrayList<>());
	    			}
	    			if(!finalMap.containsKey(iter.getValue())) {
	    				finalMap.put(iter.getValue(), new ArrayList<>());
	    			}
	    		} 
*/			} catch (IOException e) {
				e.printStackTrace();
			}
	}	
}
