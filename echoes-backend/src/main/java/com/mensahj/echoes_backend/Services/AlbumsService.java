package com.mensahj.echoes_backend.Services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mensahj.echoes_backend.DTOs.AlbumsDTO;
import com.mensahj.echoes_backend.Models.Albums;
import com.mensahj.echoes_backend.Models.Memories;
import com.mensahj.echoes_backend.Repositories.AlbumsRepo;

import jakarta.transaction.Transactional;

@Service
public class AlbumsService {
    
    @Autowired
    private AlbumsRepo albumsRepo;


    public List<Albums> getAllAlbums(){
        return albumsRepo.findAll();
    }

    public Albums getAlbumById(int id){
        return albumsRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Album not found"));
    }

    public Albums createAlbum(AlbumsDTO album) {
        Albums newAlbum = new Albums();
        newAlbum.setAlbum_id(album.getId());
        newAlbum.setTitle(album.getTitle());
        newAlbum.setDescription(album.getDescription());
        return albumsRepo.save(newAlbum);
    }

    @Transactional
    public void uploadMemoryIntoAlbum(Albums album, Memories uploadedMemory){
        if (album.getMemories().contains(uploadedMemory)){
            throw new Error("Memory has already been uploaded to this album");
        }
        if (!album.getMemories().contains(uploadedMemory)) {
            album.getMemories().add(uploadedMemory);
        }
        if (!uploadedMemory.getAlbums().contains(album)) {
            uploadedMemory.getAlbums().add(album);
        }
        albumsRepo.save(album);   
    }

    public void deleteMemoryFromAlbum(Albums album, Memories memory) {
        if (album.getMemories() != null || memory.getAlbums() != null) {
            album.getMemories().remove(memory);
            memory.getAlbums().remove(album);
            albumsRepo.save(album);
        }
    }

    public void deleteAlbumById(int id){
        albumsRepo.deleteById(id);
    }

    @Transactional
    public void deleteAlbumByTitle(String title){
        albumsRepo.deleteByTitle(title);
    }


}
