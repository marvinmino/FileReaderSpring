package com.example.demo.Controllers;

import com.example.demo.Models.Person;
import com.example.demo.Services.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class ImportController {
    private final String UPLOAD_DIR = "src/main/resources/";
    private final String FILE_ALLOWED_EXTENSION = "txt";

    @Autowired
    private ImportService importService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, Model model) throws FileNotFoundException {
        try {
            // check if file is empty
            if (file.isEmpty()) {
                importService.addFlashAttribute(attributes,"Please select a file to upload.");
                return "redirect:/";
            }

            // normalize the file path
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path path = Paths.get(UPLOAD_DIR + fileName);

            //check if file is .txt
            String extension = importService.getFileExtension(fileName);

            if (!extension.equals(FILE_ALLOWED_EXTENSION)) {
                importService.addFlashAttribute(attributes,"File must be of type .txt");
                return "redirect:/";
            }

            // save the file on the resources folder
            try {
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            importService.generateViewModelForFileImport(model, path, fileName);

        } catch (Exception e){
            importService.addFlashAttribute(attributes, "Something happened during the import");
            return "redirect:/";
        }

        return "FileReader";
    }
}
