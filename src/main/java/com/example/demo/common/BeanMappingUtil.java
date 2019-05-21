package com.example.demo.common;

import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2019/5/21/021.
 */
public class BeanMappingUtil {

    private static ModelMapper modelMapper = new ModelMapper();

    public static  <D> D map(Object source, Type destinationType) {
       return modelMapper.map(source,destinationType);
    }
}
