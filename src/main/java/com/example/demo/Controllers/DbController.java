package com.example.demo.Controllers;

import com.example.demo.Models.Person;
import com.example.demo.Repositories.PersonRepository;
import com.example.demo.Services.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DbController {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    ImportService importService;

    @GetMapping("/db-index")
    public String dbIndex(Model model) {
        List<Person> people = (List<Person>) personRepository.findAll();
        importService.addCountsToModel(model, people);

        model.addAttribute("people", people);
        return "DBIndex";
    }
}
