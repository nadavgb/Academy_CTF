package com.foco.helloworld;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.engine.AttributeName;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

@Controller
public class HelloController {

    private static final String HELLO_PAGE = "hello";
    private static final String JACKSON = "jackson";
    private static final String PATH_TRAV = "patht";
    private static final String _SSRF = "ssrf";
    private static final String _RCE = "rce";
    private static final String INSECDSRLZ = "insecdsrlz";
    private static final String CODESNIP1 = "codesnip";
    private static final String CODESNIP2 = "codesnip2";
    private static final String CODESNIP3 = "codesnip3";
    private static final String CODESNIP4 = "codesnip4";
    private static final String CODESNIP5 = "codesnip5";
    private static final String CODESNIP6 = "codesnip6";
    private static final String CODESNIP9 = "codesnip9";

    //Index
    @GetMapping("/")
    public String Index(){
        return "index.html";
    }

    //xss task
    @GetMapping("/challenge1")
    public String xss(@ModelAttribute XSS xss, Model m) {
        String payload = xss.getQuery();
        m.addAttribute("data", payload);
      return HELLO_PAGE;
    };

    //path traversal task
    @GetMapping("/challenge2")
    public String pathTraversal(@ModelAttribute pathTraversal pathT, Model m){
        String data = pathT.getFilename();
        m.addAttribute("data", data);
        return PATH_TRAV;
    }
    //SSRF task
    @GetMapping( value = "/challenge3", produces = MediaType.IMAGE_JPEG_VALUE)
    public String ssrf(@ModelAttribute SSRF ssrf, Model m) throws IOException {
        String data = ssrf.getUrl();
        m.addAttribute("data", data);
        return _SSRF;
    }

    //RCE
    @GetMapping(value = "/challenge4")
    public String hello_rce() {
        return _RCE;
    };


    @PostMapping( "/challenge4")
    public String rce(@ModelAttribute Rce rce, Model model) throws MalformedURLException, URISyntaxException, UnsupportedEncodingException {
        String output = rce.getInput();
        model.addAttribute("output", output);
        return _RCE;
    };

    //Jackson
    @GetMapping("/challenge5")
    public String jackson(@ModelAttribute insecrejackson jackson, Model model) throws JsonProcessingException {
        try{
            String data = jackson.getJson();
            model.addAttribute("data", data);
            return JACKSON;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return JACKSON;
        }
    }


    @PostMapping("/challenge5")
    public String jackson_post(@ModelAttribute insecrejackson jackson, Model model) throws JsonProcessingException {
        try{
            String data = jackson.getJson();
            model.addAttribute("data", data);
            return JACKSON;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return JACKSON;
        }
    }
    //InsecureDeserialization
    @PostMapping("/challenge9")
    public String insecDesrlz(@ModelAttribute insecureDeserialization filn, Model m)throws Exception, IOException, ClassNotFoundException{
        String data2 = filn.getFileContent();
        m.addAttribute("data2", data2);
        return INSECDSRLZ;
    }
    @GetMapping("/challenge9")
    public String insecDesrlz(){
        return INSECDSRLZ;
    }

    //code review task
    
    //xss
    @GetMapping( value = "/code_challenge1")
    public String code() throws IOException {
        return CODESNIP1;
    }
    //path traversal
    @GetMapping( value = "/code_challenge2")
    public String code2() throws IOException {
        return CODESNIP2;
    }

    //SSRF
    @GetMapping( value = "/code_challenge3")
    public String code3() throws IOException {
        return CODESNIP3;
    }

    //RCE
    @GetMapping( value = "/code_challenge4")
    public String code4() throws IOException {
        return CODESNIP4;
    }

    //PHP code flow
    @GetMapping( value = "/code_challenge5")
    public String code5() throws IOException {
        return CODESNIP5;
    }
    @GetMapping( value = "/code_challenge6")
    public String code6() throws IOException {
        return CODESNIP6;
    }

    @GetMapping( value = "/code_challenge9")
    public String code9() throws IOException {
        return CODESNIP9;
    }
}

//SQLi
//spring4shell







