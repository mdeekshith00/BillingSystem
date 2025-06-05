package com.system.configuration;

import java.util.List;
import java.util.Properties;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BillingSystemConfig implements WebMvcConfigurer {

    @Bean
    ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	
	    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	        converters.add(new MappingJackson2HttpMessageConverter());
	    }
	    
}



