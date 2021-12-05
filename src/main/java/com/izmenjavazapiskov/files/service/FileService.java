package com.izmenjavazapiskov.files.service;

import com.izmenjavazapiskov.files.entity.File;
import com.izmenjavazapiskov.files.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public List<File> getFiles() {
        return fileRepository.findAll();
    }

    public File getFileById(long id){
        return fileRepository.findByFileId(id);
    }

    public File createFile(File file) {
        return fileRepository.save(file);
    }

    public boolean deleteFile(Long id) {
        File file = fileRepository.getById(id);
        if (file != null){
            fileRepository.delete(file);
            return true;
        }
        return false;
    }
}
