package com.awstest.springboot.controller;

import com.awstest.springboot.entity.File;
import com.awstest.springboot.repository.FileRepository;
import com.awstest.springboot.repository.S3Repository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/files")
public class FileController {
    private final S3Repository s3Repository;
    private final FileRepository fileRepository;

    public FileController(S3Repository s3Repository, FileRepository fileRepository) {
        this.s3Repository = s3Repository;
        this.fileRepository = fileRepository;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<File> add(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        File fileEntity= fileRepository.save(s3Repository.addFile(file));
        return ResponseEntity.ok(fileEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> get(@PathVariable("id") Long id) {
        Optional<File> file = fileRepository.findById(id);
        if (file.isEmpty()) {
            return null;
        }

        return ResponseEntity.ok(s3Repository.getFile(file.get().getStorageName()));
    }
}
