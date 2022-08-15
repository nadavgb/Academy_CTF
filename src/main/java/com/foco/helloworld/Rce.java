package com.foco.helloworld;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Scanner;

public class Rce {
    String input;

    public static String execCmd(String cmd) {
        String result = null;
        try (InputStream inputStream = Runtime.getRuntime().exec(cmd).getInputStream();
             Scanner s = new Scanner(inputStream).useDelimiter("\\A")) {
            result = s.hasNext() ? s.next() : null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String strip_encoding(String command) throws MalformedURLException, URISyntaxException, UnsupportedEncodingException {
        String a_command = URLDecoder.decode(command, "utf-8");
        String  b_command = URLDecoder.decode(a_command, "utf-8");

        return b_command;
    }

    public String command_filter(String command){
        String r_command = java.net.URLDecoder.decode(command);
        if (r_command.contains("#")) {
            command = "invalid";
        }
        return command;
    }

    public String run_command(String commnad) throws MalformedURLException, URISyntaxException, UnsupportedEncodingException {
        commnad = command_filter(commnad);
        if (commnad == "invalid"){
            return commnad;
        }else {
            String run_me = strip_encoding(commnad);
            String result = execCmd(run_me);

            return result;
        }
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() throws MalformedURLException, URISyntaxException, UnsupportedEncodingException {
        input = run_command(input);
        return input;
    }
}
