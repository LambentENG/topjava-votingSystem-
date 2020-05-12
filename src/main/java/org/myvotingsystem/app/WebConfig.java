package org.myvotingsystem.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.myvotingsystem.app.web.json.JacksonObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.myvotingsystem.app.web"})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public MappingJackson2HttpMessageConverter getJacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(getObjectMapper());
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(getJacksonHttpMessageConverter());
        converters.add(getStringHttpMessageConverter());
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return JacksonObjectMapper.getMapper();
    }

    @Bean
    public StringHttpMessageConverter getStringHttpMessageConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("text", "plain", Charset.forName("UTF-8")),
                new MediaType("text", "html", Charset.forName("UTF-8"))));
        return converter;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuthenticationPrincipalArgumentResolver());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:messages/app");
        resource.setCacheSeconds(60);
        resource.setDefaultEncoding("UTF-8");
        resource.setFallbackToSystemLocale(false);
        return resource;
    }
}
