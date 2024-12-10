package com.mensahj.echoes_backend.Services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mensahj.echoes_backend.DTOs.LikesDTO;
import com.mensahj.echoes_backend.Models.Likes;
import com.mensahj.echoes_backend.Models.Memories;
import com.mensahj.echoes_backend.Repositories.LikesRepo;

@Service
public class LikesService {
    
    @Autowired
    private LikesRepo likesRepo;

    public List<Likes> getAllLikes(){
        return likesRepo.findAll();
    }

    public Likes likeAMemory(LikesDTO likesDTO, Memories memory){
        Likes like = new Likes();
        like.setMemory(memory);
        return likesRepo.save(like);
    }

    public void deleteALike(int id){
        if(!likesRepo.existsById(id)) throw new NoSuchElementException("Like with id " + id + " does not exist");
        likesRepo.deleteById(id);
    }

}
