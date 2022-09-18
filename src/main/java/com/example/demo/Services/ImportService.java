package com.example.demo.Services;

import com.example.demo.Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Scanner;

@Component
public class ImportService {
    @Autowired
    ViewService viewService;

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

        viewService.addCountsToModel(model, people);
        model.addAttribute("message", "File read: " + fileName + '!');
        model.addAttribute("people",people);
    }
}
