//package com.spring.application.configuration;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
//
//
//
//
//@Configuration
//public class RestConfiguration {
//    @Primary
//    @Bean
//    public ObjectMapper getObjectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        Hibernate5Module hm = new Hibernate5Module();
//        hm.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
//        mapper.registerModule(hm);
//        mapper.registerModule(new JsonOrgModule());
//        mapper.registerModule(new JavaTimeModule());
//        mapper.registerModule(new JodaModule());
//        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
//        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
//        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//        return mapper;
//    }
//
//
//}
