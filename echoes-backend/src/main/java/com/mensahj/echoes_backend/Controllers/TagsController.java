package com.mensahj.echoes_backend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mensahj.echoes_backend.Services.MemoryService;
import com.mensahj.echoes_backend.Services.TagsService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mensahj.echoes_backend.Models.Memories;
import com.mensahj.echoes_backend.Models.Tags;
import com.mensahj.echoes_backend.Models.TagsDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




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

    @GetMapping("/byId")
    public ResponseEntity<?> getTagById(@RequestParam int id) {
        Tags tag = service.getTagById(id);
        return ResponseEntity.ok(tag);
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

    @PutMapping("modifyTag/{id}")
    public ResponseEntity<?> modifyTag(@PathVariable int id, @RequestBody TagsDTO tagDTO) {
        Tags convertedTag = service.convertToTags(tagDTO);
        Tags modifiedTag = service.modifyTag(id, convertedTag);
    
        return ResponseEntity.ok(modifiedTag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable int id){
        service.deleteTagById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    
}
