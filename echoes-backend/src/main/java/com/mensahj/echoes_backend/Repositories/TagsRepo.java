package com.mensahj.echoes_backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mensahj.echoes_backend.Models.Tags;


public interface TagsRepo extends JpaRepository<Tags, Integer>{
    
}
