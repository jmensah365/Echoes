package com.mensahj.echoes_backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mensahj.echoes_backend.Models.Albums;

public interface AlbumsRepo extends JpaRepository<Albums, Integer>{
    
}
