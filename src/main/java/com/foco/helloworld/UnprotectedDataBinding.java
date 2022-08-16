package com.foco.helloworld;

import lombok.Data;

// Configure lombok data binding
@Data
public class UnprotectedDataBinding {

    private String isAdmin;
    private String username, password;

    // This function checks if the provided user & password match and then update permmisions accordinly
    public boolean Auth(){

        // Check if user and password are not empty
        if(username != null && password != null){

            // Check Authentication for admin User
            if (String.valueOf(username.toLowerCase()).equals("admin") && String.valueOf(password.hashCode()).equals("1216985755")) {
                isAdmin = "yes";
                return true;   
            } 
            // Check authentication for avigdor user
            if (String.valueOf(username).equals("avigdor") && String.valueOf(password).equals("JsecAcademy")) {
                return true;
            }
            
        }

        // Return false if authentication fails
        return false;
    }
    
}
