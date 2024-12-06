package com.mensahj.echoes_backend.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mensahj.echoes_backend.Models.Albums;
import com.mensahj.echoes_backend.Models.AlbumsDTO;
import com.mensahj.echoes_backend.Models.Memories;
import com.mensahj.echoes_backend.Services.AlbumsService;
import com.mensahj.echoes_backend.Services.MemoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/albums")
public class AlbumsController {
    
    @Autowired
    private AlbumsService albumsService;


    @Autowired
    private MemoryService memoryService;

    @GetMapping("/findAllAlbums")
    public List<Albums> getAllAlbums() {
        return albumsService.getAllAlbums();
    }

    
    @PostMapping("/createAlbum")
    public ResponseEntity<?> createAlbum(@RequestBody AlbumsDTO album) {
        Albums createdAlbum = albumsService.createAlbum(album);
        return ResponseEntity.ok(createdAlbum);
    }
    
    @PostMapping("/addMemory")
    public ResponseEntity<String> addMemoryToAlbum(@RequestParam("albumId") int albumId, @RequestParam(value = "image" , required = false) MultipartFile photo, @RequestParam(required = false) Integer memoryId) throws IOException{
        Albums album = albumsService.getAlbumById(albumId);
        Memories memory = new Memories(); 

        if (memoryId != null) {
            memory = memoryService.getMemoryById(memoryId);
        } else if( photo != null) {
            memory = memoryService.uploadMemoryToFileSystem(photo);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Either memoryId or image file must be provided");
        }

        albumsService.uploadMemoryIntoAlbum(album, memory);

        return ResponseEntity.ok("Memory added successfully to album");
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable("albumId") int albumId){
        albumsService.deleteAlbumById(albumId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/byTitle")
    public ResponseEntity<Void> deleteAlbumByTitle(@RequestParam("album title") String title){
        albumsService.deleteAlbumByTitle(title);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteMemory")
    public ResponseEntity<Void> deleteMemoryFromAlbum(@RequestParam("albumId") int albumId, @RequestParam("memoryId") int memoryId){
        Albums album = albumsService.getAlbumById(albumId);
        Memories memory = memoryService.getMemoryById(memoryId);
        albumsService.deleteMemoryFromAlbum(album, memory);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
