package com.foco.helloworld;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class pathTraversal {
    private String filename;

    public static String read_file(String file_path)  {
        String str = "";
        Path Get_Path = Paths.get(file_path);

        try{
            str = new String(Files.readAllBytes(Get_Path));
        }catch (IOException e){
            return "Error";
        }


//        String BASE_PATH = "/Users/liavg/Desktop/tmp/";
//        String Get_Path = Paths.get(file_path).toString();
//        Path A_FILE_PATH = Paths.get(BASE_PATH + Get_Path);
//
//        if (A_FILE_PATH.toString().contains("../")){
//            return "Error";
//        }else {
//            String str = "";
//
//
//            try {
//                str = new String(
//                        Files.readAllBytes(A_FILE_PATH));
//            }
//            catch (IOException e) {
//                return "Error";
//                //e.printStackTrace();
//            }
//
//            return str;
//        }


        return str;

    }


    public String getFilename() {
        filename = read_file(filename);
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
