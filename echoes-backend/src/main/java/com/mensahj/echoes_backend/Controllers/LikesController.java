package com.mensahj.echoes_backend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mensahj.echoes_backend.DTOs.LikesDTO;
import com.mensahj.echoes_backend.Models.Likes;
import com.mensahj.echoes_backend.Models.Memories;
import com.mensahj.echoes_backend.Services.LikesService;
import com.mensahj.echoes_backend.Services.MemoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/likes")
public class LikesController {
    
    @Autowired
    private LikesService likesService;

    @Autowired
    private MemoryService memoryService;

    @GetMapping("/getAllLikes")
    public List<Likes> getAllLikes() {
        return likesService.getAllLikes();
    }

    @PostMapping()
    public ResponseEntity<?> likeAMemory(@RequestBody LikesDTO like, @RequestParam("memoryId") Integer memoryId) {

        Memories memory = new Memories();
        if (memoryId != null){
            memory = memoryService.getMemoryById(memoryId);
        }
        Likes likedMemory = likesService.likeAMemory(like, memory);
        
        return ResponseEntity.ok(likedMemory);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteALike(@PathVariable Integer id){
        likesService.deleteALike(id);
        return ResponseEntity.noContent().build();
    }
    
}
