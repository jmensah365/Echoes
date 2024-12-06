package com.mensahj.echoes_backend.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;

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
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/jpeg")).body(imageData);
    }

    // @GetMapping("/all")
    // public List<ResponseEntity<byte[]>> getAllMemories() throws IOException{

    //     List<byte[]> memoryFiles = service.downloadAllMemoriesFromFileSystem();

    //     List<ResponseEntity<byte[]>> responseEntities = new ArrayList<>();

    //     if(!memoryFiles.isEmpty()) {
    //         for (byte[] memoryFile : memoryFiles) {
    //             HttpHeaders headers = new HttpHeaders();
    //             headers.setContentType(MediaType.IMAGE_JPEG);
    //             responseEntities.add(ResponseEntity.status(HttpStatus.OK).headers(headers).body(memoryFile));
    //         }
    //     } else {
    //         responseEntities.add(ResponseEntity.status(HttpStatus.NO_CONTENT).body("No image found".getBytes()));
    //     }

    //     return responseEntities;

    //     // MultiValueMap<String, Object> memoryList = new LinkedMultiValueMap<>();
    
    //     // for (int i = 0; i < memoryFiles.size(); i++) {
    //     //     byte[] fileData = memoryFiles.get(i);
            
    //     //     memoryList.add("Image" + i, fileData);
    //     // }
    //     // return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/jpeg")).body(memoryList);
    // }
    
    

    @PostMapping
    public ResponseEntity<?> uploadMemory(@RequestParam("image") MultipartFile file) throws IOException {
        Memories uploadImage = service.uploadMemoryToFileSystem(file);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Memory uploaded successfully");
        response.put("memoryId", uploadImage.getMemoryId());
        response.put("filePath", uploadImage.getFilePath());
        response.put("title", uploadImage.getTitle());
        response.put("uploadedAt", uploadImage.getUploadedAt());

        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMemory(@RequestParam String title){
        service.deleteMemoryByTitle(title);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
