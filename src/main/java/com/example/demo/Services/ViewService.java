package com.example.demo.Services;

import com.example.demo.Models.Person;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

@Component
public class ViewService {
    public void addFlashAttribute(RedirectAttributes attributes, String message){
        attributes.addFlashAttribute("message", message);
    }

    public void addCountsToModel(Model model, List<Person> people){
        HashSet<String> names = new HashSet<String>();
        HashSet<String> cities = new HashSet<String>();

        for (Person person: people) {
            names.add(person.getName());
            cities.add(person.getCity());
        }
        model.addAttribute("city_count",cities.size());
        model.addAttribute("names_count",names.size());
    }
}
