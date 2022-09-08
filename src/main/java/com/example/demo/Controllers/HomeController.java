package com.example.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.jar.Attributes;

@Controller
public class HomeController {
    @GetMapping
    public String home(Model model) {
       return "Home";
    }
}
