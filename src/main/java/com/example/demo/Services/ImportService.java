package com.example.demo.Services;

import com.example.demo.Models.Person;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
@Component
public class ImportService {
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
        HashSet<String> names = new HashSet<String>();
        HashSet<String> cities = new HashSet<String>();

        Scanner scanner = new Scanner(new File(path.toUri()));
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Person person = new Person(data.split(","));
            names.add(person.get_name());
            cities.add(person.get_city());
            people.add(person);
        }
        scanner.close();

        // return success response
        model.addAttribute("message", "File read: " + fileName + '!');
        model.addAttribute("people",people);
        model.addAttribute("city_count",cities.size());
        model.addAttribute("names_count",names.size());
    }

    public void addFlashAttribute(RedirectAttributes attributes,String message){
        attributes.addFlashAttribute("message", message);
    }
}
