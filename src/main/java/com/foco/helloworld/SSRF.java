package com.foco.helloworld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SSRF {
    private String url;

    public String do_get(String url) throws IOException {
        StringBuffer content = new StringBuffer();
        URL uri = new URL(url);
        URLConnection yc = uri.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            content.append(inputLine);
        in.close();
        return content.toString();
    }

    public String getUrl() throws IOException {
        url = do_get(url);
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
