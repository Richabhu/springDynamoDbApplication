package com.spring.application.utils;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshaller;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.spring.application.models.enumeration.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//public class EnumMarshallConverter implements DynamoDBMarshaller<List<Product>> {
//
//    @Override
//    public String marshall(List<Product> products) {
//        List<String>transportMap=new ArrayList<>();
//        for(Product product:products){
//            transportMap.add(product.name());
//        }
//        return transportMap.toString().replaceAll("\\[|\\]", "");//Save as comma separate value for the purpose of easiness to unmarshall
//    }
//
//    @Override
//    public List<Product> unmarshall(Class<List<Product>> aClass, String s) {
//        List<String>map= Arrays.asList(s.split("\\s*,\\s*")); //split from comma and parse to List
//        List<Product>products=new ArrayList<>();
//        for (String st:map){
//            products.add(Product.valueOf(st));
//        }
//        return products;
//    }
//}

//public class EnumMarshallConverter implements DynamoDBTypeConverter<List<String>, List<Product>> {
//
//    @Override
//    public List<String> convert(List<Product> object) {
//        List<String> result = new ArrayList<String>();
//        if (object != null) {
//            object.stream().forEach(e -> result.add(e.name()));
//        }
//        return result;
//    }
//
//    @Override
//    public List<Product> unconvert(List<String> object) {
//        List<Product> result = new ArrayList<Product>();
//        if (object != null) {
//            object.stream().forEach(e -> result.add(Product.valueOf(e)));
//        }
//        return result;
//    }
//}


public class EnumMarshallConverter implements DynamoDBTypeConverter<String, List<Product>> {

    @Override
    public String convert(List<Product> object) {
        List<String> result = new ArrayList<String>();
        if (object != null) {
            object.stream().forEach(e -> result.add(e.name()));
        }
        return result.toString().replaceAll("\\[|\\]", "");
    }

    @Override
    public List<Product> unconvert(String object) {
        List<Product> result = new ArrayList<Product>();
        List<String> map= Arrays.asList(object.split("\\s*,\\s*"));
        if (object != null) {
            map.stream().forEach(e -> result.add(Product.valueOf(e)));
        }
        return result;
    }
}