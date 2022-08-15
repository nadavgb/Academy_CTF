package com.foco.helloworld;

import java.util.Locale;

public class XSS {
    private String query;


    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        if (query.toLowerCase().contains("script")){
            return "are you kidding me?! try again ...";
        }else {
            return query;
        }

    }
}


