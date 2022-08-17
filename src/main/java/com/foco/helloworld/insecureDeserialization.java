package com.foco.helloworld;
//
import java.io.*;
import java.util.Base64;
import lombok.Getter;
import lombok.Setter;
//
@Setter
@Getter
public class insecureDeserialization implements Serializable{
   private final String BASE64ERROR = "Base64 Only!";
   private final String CLASSNOTFOUNDERROR = "Try Again :)";
   private final String FLAG = "{NADAV_HA_GEVER!}"; 
   //
   private String filecontent;
   

   // Filecontent getter
   public String getFileContent() throws IOException{
      try{
         byte[] decoded = Base64.getDecoder().decode(filecontent);
         InputStream stream = new ByteArrayInputStream(decoded);
         ObjectInputStream ois = new ObjectInputStream(stream);
         NormalObj unserObj = (NormalObj)ois.readObject();
         ois.close();

      }
      catch (ClassNotFoundException e){
         return CLASSNOTFOUNDERROR;
      }
      catch(StreamCorruptedException e){
         return BASE64ERROR;
      }
      catch(ClassCastException e){
         filecontent = FLAG;
      }
      return filecontent;
   }

   class NormalObj implements Serializable{
      public String name;
      public NormalObj(String name){
          this.name = name;
      }
  
      private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
          in.defaultReadObject();
          System.out.println(this.name);
      }
  }
  

 
}


class Liav implements Serializable{
   public String gutman;

   public Liav(String gutman){
       this.gutman = gutman;
   }

   private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException, ClassCastException{
       in.defaultReadObject();
       System.out.println(gutman);
   }
}


class VulnObj implements Serializable{
   public String cmd;
   public VulnObj(String cmd){
       this.cmd = cmd;
   }


   private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
       in.defaultReadObject();
       String s = null;
       Process p = Runtime.getRuntime().exec(this.cmd);
       BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
       while ((s = stdInput.readLine()) != null) {
           System.out.println(s);
       }
   }
}