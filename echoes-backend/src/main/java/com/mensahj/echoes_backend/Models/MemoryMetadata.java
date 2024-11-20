package com.mensahj.echoes_backend.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "memory_metadata")
public class MemoryMetadata {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer metadataId;

    //Key = Resolution (1920x1080)
    private String key;

    //Value = (1920x1080)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id")
    private Memories memory;

    public Integer getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(Integer metadataId) {
        this.metadataId = metadataId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Memories getMemory() {
        return memory;
    }

    public void setMemory(Memories memory) {
        this.memory = memory;
    }

    

}
