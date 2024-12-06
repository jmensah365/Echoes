package com.mensahj.echoes_backend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mensahj.echoes_backend.Services.MemoryService;
import com.mensahj.echoes_backend.Services.TagsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mensahj.echoes_backend.Models.Memories;
import com.mensahj.echoes_backend.Models.Tags;
import com.mensahj.echoes_backend.Models.TagsDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/tags")
public class TagsController {
    
    @Autowired
    private TagsService service;

    @Autowired
    private MemoryService memoryService;

    @GetMapping("/getAll")
    public List<Tags> getAllTags() {
        return service.getAllTags();
    }

    @PostMapping("/createTag")
    public ResponseEntity<?> createTag (@RequestBody TagsDTO tag, @RequestParam("memoryId") Integer memoryId) {
        Tags createdTag = service.createTag(tag);

        Memories memory = new Memories();
        if (memoryId != null) {
            memory = memoryService.getMemoryById(memoryId);
        }

        service.addTagToMemory(createdTag, memory);
        
        return ResponseEntity.ok(createdTag);
    }
    
    
    
}
