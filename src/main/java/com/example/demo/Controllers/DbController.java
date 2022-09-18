package com.example.demo.Controllers;

import com.example.demo.Models.Person;
import com.example.demo.Repositories.PersonRepository;
import com.example.demo.Services.PersonService;
import com.example.demo.Services.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class DbController {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @Autowired
    ViewService viewService;

    @GetMapping("/db-index")
    public String dbIndex(Model model) {
        List<Person> people = (List<Person>) personRepository.findAll();
        viewService.addCountsToModel(model, people);

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
    public String updatePerson(@PathVariable(value="id") Long id, @Valid @ModelAttribute Person person, Errors errors, RedirectAttributes attributes) {
        try{
            if (null != errors && errors.getErrorCount() == 0) {
                person.setId(id);
                personRepository.save(person);
                attributes.addAttribute("message","User updated successfully");
            } else{
                attributes.addAttribute("message", "Please Fill the right datay!!");
            }
        } catch(Exception e){
            String s = e.getMessage();
        }

        return "redirect:/db-index";
    }

    @PostMapping("/save-person")
    public String savePerson(@Valid @ModelAttribute Person person, Errors errors, RedirectAttributes attributes) {
        try{
            if (null != errors && errors.getErrorCount() == 0) {
                attributes.addAttribute("message", "Details saved successfully!!");
                personRepository.save(person);
            } else{
                attributes.addAttribute("message", "Please Fill the right datay!!");
            }
        } catch(Exception e){
            String s = e.getMessage();
        }

        return "redirect:/db-index";
    }
}
