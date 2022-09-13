package com.example.demo.Controllers;

import com.example.demo.Models.Person;
import com.example.demo.Repositories.PersonRepository;
import com.example.demo.Services.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping(value = {"/edit-or-store-person/{id}", "/edit-or-store-person/"})
    public String editOrStorePerson(@PathVariable(value="id") Optional<Long> id, Model model) {
        if(id.isPresent()){
            Optional<Person> optionalPerson = personRepository.findById(id.get());
            Person person = optionalPerson.get();
            model.addAttribute("person", person);
        }

        return "CreateOrUpdatePerson";
    }

    @PostMapping("/update-person/{id}")
    public String updatePerson(@PathVariable(value="id") Long id, @RequestParam Map<String,String> requestParams, RedirectAttributes attributes) {
        Person person = importService.mapPersonResource(requestParams);;
        person.setId(id);
        personRepository.save(person);
        attributes.addAttribute("message","User updated successfully");

        return "redirect:/db-index";
    }

    @PostMapping("/save-person")
    public String savePerson(@RequestParam Map<String,String> requestParams, RedirectAttributes attributes) {
        Person person = importService.mapPersonResource(requestParams);
        personRepository.save(person);
        attributes.addAttribute("message","User saved successfully");

        return "redirect:/db-index";
    }
}
