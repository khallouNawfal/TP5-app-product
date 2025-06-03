package org.example.products_management_spring_mvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class SecurityController {
    @GetMapping("/NotAuthorized")
    public String NotAuthorized(){
        return "NotAuthorized";
    }
}
