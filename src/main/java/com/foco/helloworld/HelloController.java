package com.foco.helloworld;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

@Controller
public class HelloController {

    private static final String HELLO_PAGE = "hello";
    private static final String JACKSON = "jackson";

    private static final String CHALLENGE7_ADMIN_TEMPLATE = "admin";
    private static final String CHALLENGE7_USER_TEMPLATE = "user";

    private static final String PATH_TRAV = "patht";
    private static final String _SSRF = "ssrf";
    private static final String _RCE = "rce";


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

    //code review task

    
    //xss
    @GetMapping( value = "/code_challenge1")
    public String code() throws IOException {
        return "codesnip";
    }
    //path traversal
    @GetMapping( value = "/code_challenge2")
    public String code2() throws IOException {
        return "codesnip2";
    }

    //SSRF
    @GetMapping( value = "/code_challenge3")
    public String code3() throws IOException {
        return "codesnip3";
    }

    //RCE
    @GetMapping( value = "/code_challenge4")
    public String code4() throws IOException {
        return "codesnip4";
    }

    //PHP code flow
    @GetMapping( value = "/code_challenge5")
    public String code5() throws IOException {
        return "codesnip5";
    }
    @GetMapping( value = "/code_challenge6")
    public String code6() throws IOException {
        return "codesnip6";
    }

    //Unprotected Data Binding 
    @GetMapping( value = "/challenge7")
    public String code7(@ModelAttribute UnprotectedDataBinding dbind, Model m) throws IOException {

        // Check if user is admin and return the right resource template
        // TODO: Change isAdmin to type boolean
        if(dbind.getIsAdmin() != null){
            return CHALLENGE7_ADMIN_TEMPLATE;
        } else {
            return CHALLENGE7_USER_TEMPLATE;
        }

    }
}

//SQLi
//spring4shell
//







