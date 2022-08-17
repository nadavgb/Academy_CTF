package com.foco.helloworld;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingRequestHeaderException;
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
    private static final String _BAC_USER = "bac_user";
    private static final String _BAC_ADMIN = "bac_admin";
    private static final String CHALLENGE7_ADMIN_TEMPLATE = "admin";
    private static final String CHALLENGE7_USER_TEMPLATE = "user";
    private static final String CHALLENGE7_LOGIN_TEMPLATE = "login";
    private static final String CHALLENGE7_CODE = "/code_challenge7";
    private static final String CHALLENGE7_CODESNIP = "codesnip7";
    private static final String CHALLENGE7_URL = "/challenge7";

    // Index
    @GetMapping("/")
    public String Index() {
        return "index.html";
    }

    // xss task
    @GetMapping("/challenge1")
    public String xss(@ModelAttribute XSS xss, Model m) {
        String payload = xss.getQuery();
        m.addAttribute("data", payload);
        return HELLO_PAGE;
    };

    // path traversal task
    @GetMapping("/challenge2")
    public String pathTraversal(@ModelAttribute pathTraversal pathT, Model m) {
        String data = pathT.getFilename();
        m.addAttribute("data", data);
        return PATH_TRAV;
    }

    // SSRF task
    @GetMapping(value = "/challenge3", produces = MediaType.IMAGE_JPEG_VALUE)
    public String ssrf(@ModelAttribute SSRF ssrf, Model m) throws IOException {
        String data = ssrf.getUrl();
        m.addAttribute("data", data);
        return _SSRF;
    }

    // RCE
    @GetMapping(value = "/challenge4")
    public String hello_rce() {
        return _RCE;
    };

    @PostMapping("/challenge4")
    public String rce(@ModelAttribute Rce rce, Model model)
            throws MalformedURLException, URISyntaxException, UnsupportedEncodingException {
        String output = rce.getInput();
        model.addAttribute("output", output);
        return _RCE;
    };

    // Jackson
    @GetMapping("/challenge5")
    public String jackson(@ModelAttribute insecrejackson jackson, Model model) throws JsonProcessingException {
        try {
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
        try {
            String data = jackson.getJson();
            model.addAttribute("data", data);
            return JACKSON;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return JACKSON;
        }
    }

    // Unprotected Data Binding
    @RequestMapping(value = CHALLENGE7_URL)
    public String code7(@ModelAttribute UnprotectedDataBinding dbind, Model m) throws IOException {

        // Trying to authenticate the user with the provided username and password
        // Return admin page if user is authenticated as admin and user page if user is
        // not admin
        if (dbind.Auth()) {
            return (dbind.getIsAdmin() != null) ? CHALLENGE7_ADMIN_TEMPLATE : CHALLENGE7_USER_TEMPLATE;
        }
        // Return Login page if auth fails
        return CHALLENGE7_LOGIN_TEMPLATE;
    }

    // InsecureDeserialization
    @PostMapping("/challenge9")
    public String insecDesrlz(@ModelAttribute insecureDeserialization filn, Model m)
            throws Exception, IOException, ClassNotFoundException {
        String data2 = filn.getFileContent();
        m.addAttribute("data2", data2);
        return INSECDSRLZ;
    }

    @GetMapping("/challenge9")
    public String insecDesrlz() {
        return INSECDSRLZ;
    }

    // START of Challenge 8 - Broken Acccess Control
    @GetMapping("/challenge8")
    public String headerBypass() {
        return _BAC_USER;
    }

    // Get the necessary HTTP headers and generate auth token
    @GetMapping("/admindashboard")
    public String header(@RequestHeader("X-Auth-Token") String authLoginToken, @RequestHeader("Host") String hostHeader,
            @RequestHeader("User-Agent") String userAgentHeader, @ModelAttribute BAC header, Model m) {
        if (header.isAuthorized(hostHeader, userAgentHeader, authLoginToken)) {
            return _BAC_ADMIN;
        } else {
            m.addAttribute("data", "You are not authorized to access the Admin Dashboard.");
            return _BAC_USER;
        }
    }

    // Catch `MissingRequestHeaderException` when trying to access the admin
    // dashboard without the necessary header
    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleMissingRequestHeaderException(MissingRequestHeaderException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                // .body(exception.getMessage());
                .body("You are not authorized to access the Admin Dashboard.");
    }
    // END of Challenge 8 - Broken Acccess Control

    // code review task

    // xss
    @GetMapping(value = "/code_challenge1")
    public String code() throws IOException {
        return CODESNIP1;
    }

    // path traversal
    @GetMapping(value = "/code_challenge2")
    public String code2() throws IOException {
        return CODESNIP2;
    }

    // SSRF
    @GetMapping(value = "/code_challenge3")
    public String code3() throws IOException {
        return CODESNIP3;
    }

    // RCE
    @GetMapping(value = "/code_challenge4")
    public String code4() throws IOException {
        return CODESNIP4;
    }

    // PHP code flow
    @GetMapping(value = "/code_challenge5")
    public String code5() throws IOException {
        return CODESNIP5;
    }

    @GetMapping(value = "/code_challenge6")
    public String code6() throws IOException {
        return CODESNIP6;
    }

    @GetMapping(value = CHALLENGE7_CODE)
    public String code7() throws IOException {
        return CHALLENGE7_CODESNIP;
    }

    @GetMapping(value = "/code_challenge9")
    public String code9() throws IOException {
        return CODESNIP9;
    }

    @GetMapping(value = "/code_challenge8")
    public String code8() throws IOException {
        return "codesnip8";
    }

    // Broken Access Control

}

// SQLi
// spring4shell
