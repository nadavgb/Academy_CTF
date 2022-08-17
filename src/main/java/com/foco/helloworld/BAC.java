package com.foco.helloworld;

import java.util.Base64;

public class BAC {

    private static final String adminAuth = "isAdminAuth";

    public String generateAuthToken(String hostHeader, String userAgentHeader) {
        String combinedHeaders = hostHeader + ',' + userAgentHeader + ',' + adminAuth;
        String generatedAuthToken = Base64.getEncoder().encodeToString(combinedHeaders.getBytes());

        return generatedAuthToken;
    }

    public Boolean isAuthorized(String hostHeader, String userAgentHeader, String authLoginToken) {
        String generatedAuthToken = generateAuthToken(hostHeader, userAgentHeader);
        
        if (authLoginToken.equals(generatedAuthToken)) {
            return true;
        } else {
            return false;
        }
    }

}