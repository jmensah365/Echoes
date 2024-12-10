package com.mensahj.echoes_backend.Services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mensahj.echoes_backend.Repositories.MemoryRepo;
import com.mensahj.echoes_backend.Repositories.TagsRepo;

import jakarta.transaction.Transactional;

import com.mensahj.echoes_backend.DTOs.TagsDTO;
import com.mensahj.echoes_backend.Models.Memories;
import com.mensahj.echoes_backend.Models.Tags;

@Service
public class TagsService {
    @Autowired
    private TagsRepo tagsRepo;

    @Autowired
    private MemoryRepo memoryRepo;


    public List<Tags> getAllTags(){
        return tagsRepo.findAll();
    }

    public Tags getTagById(int id){
        return tagsRepo.findById(id).orElseThrow(() -> new NoSuchElementException("There is no tag with id: " + id));
    }

    public Tags createTag(TagsDTO tag){
        Tags newTag = new Tags();
        newTag.setName(tag.getName());
        return tagsRepo.save(newTag);
    }

    @Transactional
    public void addTagToMemory(Tags tag, Memories taggedMemory){

        if (!tag.getMemories().contains(taggedMemory)) tag.getMemories().add(taggedMemory);
        
        if (!taggedMemory.getTags().contains(tag)) taggedMemory.getTags().add(tag);
        
        tagsRepo.save(tag);
        memoryRepo.save(taggedMemory);
    }

    public Tags modifyTag(int id, Tags tag){
        if(!tagsRepo.existsById(id)) throw new NoSuchElementException("Tag with id " + id + " does not exist");
        tag.setTagId(id);
        return tagsRepo.save(tag);
    }

    @Transactional
    public void deleteTagById(int id){
        if(!tagsRepo.existsById(id)) throw new NoSuchElementException("Tag with id " + id + " does not exist");
        tagsRepo.deleteById(id);
    }

    public Tags convertToTags(TagsDTO tagDTO){
        Tags tag = new Tags();
        tag.setName(tagDTO.getName());

        return tag;
    }


}
