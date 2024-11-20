package com.mensahj.echoes_backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mensahj.echoes_backend.Models.MemoryMetadata;

public interface MetadataRepo extends JpaRepository<MemoryMetadata, Integer>{
    
}
