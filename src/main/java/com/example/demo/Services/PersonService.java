package com.example.demo.Services;

import com.example.demo.Models.Person;
import com.example.demo.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

@Component
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public Person mapPersonResource(Map<String,String> requestParams){
        Person person = new Person();

        person.setName(requestParams.get("name"));
        person.setSurname(requestParams.get("surname"));
        person.setCity(requestParams.get("city"));
        person.setAddress(requestParams.get("address"));

        return person;
    }
}
