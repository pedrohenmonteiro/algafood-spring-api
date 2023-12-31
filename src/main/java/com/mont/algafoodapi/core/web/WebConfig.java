package com.mont.algafoodapi.core.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
      .allowedMethods("*");
    //   .maxAge(30);
    }

    @Bean
    public Filter shallowEtagHeaderFilter() {
      return new ShallowEtagHeaderFilter();
    }

     @Bean
    public HttpMessageConverter<?> jsonMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }
}
