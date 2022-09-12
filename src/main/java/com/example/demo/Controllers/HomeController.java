package com.example.demo.Controllers;

import com.example.demo.Models.Person;
import com.example.demo.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String home(Model model) {
       return "Home";
    }

    @GetMapping("/file-index")
    public String fileIndex(Model model) {
        return "FileIndex";
    }
}

