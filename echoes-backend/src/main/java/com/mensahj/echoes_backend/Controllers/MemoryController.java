package com.mensahj.echoes_backend.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mensahj.echoes_backend.Services.MemoryService;
import com.mensahj.echoes_backend.Models.Memories;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/memories")
public class MemoryController {
    
    private MemoryService service;
    public MemoryController(MemoryService service){
        this.service = service;
    }

    
    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadMemory(@PathVariable String fileName) throws IOException {
        byte[] imageData = service.downloadMemoryFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    }
    

    @PostMapping
    public ResponseEntity<?> uploadMemory(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = service.uploadMemoryToFileSystem(file);
        
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMemory(@RequestParam String title){
        service.deleteMemoryByTitle(title);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
