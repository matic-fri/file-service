package com.izmenjavazapiskov.files.controller;

import com.izmenjavazapiskov.files.entity.File;
import com.izmenjavazapiskov.files.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/files")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public List<File> getFiles(){
        return fileService.getFiles();
    }

    @PostMapping("/")
    public File uploadFile(InputStream uploadedInputStream){
        byte[] bytes = new byte[0];
        try (uploadedInputStream) {
            bytes = uploadedInputStream.readAllBytes();
            return fileService.createFile(by);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
