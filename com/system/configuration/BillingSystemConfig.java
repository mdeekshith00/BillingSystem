package com.system.configuration;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BillingSystemConfig implements WebMvcConfigurer {
	
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	
	    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	        converters.add(new MappingJackson2HttpMessageConverter());
	    }
	
}
