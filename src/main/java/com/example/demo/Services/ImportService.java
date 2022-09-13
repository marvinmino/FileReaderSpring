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
public class ImportService {
    @Autowired
    PersonRepository personRepository;

    public String getFileExtension(String str){
        String extension = "";
        int index = str.lastIndexOf('.');
        if (index > 0) {
            extension = str.substring(index + 1);
        }
        return extension;
    }
    public void generateViewModelForFileImport(Model model, Path path, String fileName) throws FileNotFoundException {

        LinkedList<Person> people = new LinkedList<Person>();

        Scanner scanner = new Scanner(new File(path.toUri()));
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Person person = new Person(data.split(","));
            people.add(person);
        }
        scanner.close();

        addCountsToModel(model, people);
        model.addAttribute("message", "File read: " + fileName + '!');
        model.addAttribute("people",people);
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

    public void addFlashAttribute(RedirectAttributes attributes,String message){
        attributes.addFlashAttribute("message", message);
    }

    public Person mapPersonResource(Map<String,String> requestParams){
        Person person = new Person();

        person.setName(requestParams.get("name"));
        person.setSurname(requestParams.get("surname"));
        person.setCity(requestParams.get("city"));
        person.setAddress(requestParams.get("address"));

        return person;
    }
}
