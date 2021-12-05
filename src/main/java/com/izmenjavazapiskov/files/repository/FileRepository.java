package com.izmenjavazapiskov.files.repository;

import com.izmenjavazapiskov.files.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    File findByFileId(long id);

}
