package com.foco.helloworld;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foco.helloworld.Car;

import java.io.IOException;

public class insecrejackson {
    private static ObjectMapper objectMapper;
    String json;

    /*public String insecre_jackson_main(String input) throws JsonProcessingException {
        Car car = getObjectMapper().readValue(input, Car.class);
        String data = car.toString();
        return data;
    }*/


    public String getJson() throws JsonProcessingException {
        //String jsont = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        String jsont = json;
        Car car = null;
        try {
            car = getObjectMapper().readValue(jsont, Car.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return car.toString();
    }

    public static ObjectMapper getObjectMapper(){
        if(objectMapper == null){
            objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        }
        return objectMapper;
    }



    public void setJson(String json) {
        this.json = json;
    }
}

