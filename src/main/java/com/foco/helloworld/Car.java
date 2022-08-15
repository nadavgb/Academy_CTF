package com.foco.helloworld;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data // standard getters setters
public class Car  {

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
    private final String COLOR = "Color: ";
    private final String TYPE = "Type: ";

    private String color;
    private Object type;

    // standard getters setters
//    @Override
//    public String toString(){
//        return String.join(" ",COLOR, color, TYPE, (CharSequence) type);
//    }
}
