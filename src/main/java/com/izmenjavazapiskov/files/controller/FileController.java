package com.izmenjavazapiskov.files.controller;

import com.izmenjavazapiskov.files.entity.File;
import com.izmenjavazapiskov.files.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/files")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("")
    public List<File> getFiles(){
        return fileService.getFiles();
    }

    @GetMapping("/{id}")
    public File getOneFile(@PathVariable("id") long fileId){
        return fileService.getFileById(fileId);
    }

    @PostMapping("/new")
    public File uploadFile(@RequestBody File file){
        return fileService.createFile(file);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteFile(@PathVariable("id") long fileId){
        return fileService.deleteFile(fileId);
    }
}
