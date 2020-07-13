package com.jay.dictionary.constants;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DictionaryConfiguration {
	
	@Value("${edit.distance.threshold}")
	public String EDIT_DISTANCE_THRESHOLD;
	
	@Value("${suggession.size}")
	public String SUGGESSION_SIZE;
	
	@Value("${config.log.print.flag}")
	public String CONFIG_LOG_PRINT_FLAG;
	
	private Map<String, String> dictionaryConfig;
	
	@Autowired	
	public DictionaryConfiguration() {
		
	}
	
	@PostConstruct
	public void dictionaryConfiguration()  {
		dictionaryConfig = new HashMap<String, String>();
		
		dictionaryConfig.put(DictionaryConstants.EDIT_DISTANCE_THRESHOLD, EDIT_DISTANCE_THRESHOLD);
		dictionaryConfig.put(DictionaryConstants.SUGGESSION_SIZE, SUGGESSION_SIZE);
		dictionaryConfig.put(DictionaryConstants.CONFIG_LOG_PRINT_FLAG, CONFIG_LOG_PRINT_FLAG);
		if(Boolean.parseBoolean(dictionaryConfig.get(DictionaryConstants.CONFIG_LOG_PRINT_FLAG))) {
			printConfiguration();
		}
	}
	
	private void printConfiguration() {
		System.out.println("***************************************");
		System.out.println("*******DictionaryConfiguration*********");
		System.out.println(DictionaryConstants.EDIT_DISTANCE_THRESHOLD + ": " + dictionaryConfig.get(DictionaryConstants.EDIT_DISTANCE_THRESHOLD));
		System.out.println(DictionaryConstants.SUGGESSION_SIZE + ": " + dictionaryConfig.get(DictionaryConstants.SUGGESSION_SIZE));
		System.out.println("***************************************");
	}
	
	public Map<String, String> getDictionaryConfiguration() {
		return dictionaryConfig;
	}
	
}
