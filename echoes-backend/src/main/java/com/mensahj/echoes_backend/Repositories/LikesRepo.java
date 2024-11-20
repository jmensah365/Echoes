package com.mensahj.echoes_backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mensahj.echoes_backend.Models.Likes;

public interface LikesRepo extends JpaRepository<Likes, Integer> {
    
}
