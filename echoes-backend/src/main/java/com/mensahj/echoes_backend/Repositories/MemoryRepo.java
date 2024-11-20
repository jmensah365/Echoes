package com.mensahj.echoes_backend.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mensahj.echoes_backend.Models.Memories;

public interface MemoryRepo extends JpaRepository<Memories, Integer>{
    Optional<Memories> findByTitle (String title);

    void deleteByTitle (String title);

    boolean existsByTitle(String title);

    
}
