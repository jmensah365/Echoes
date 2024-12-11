package com.mensahj.echoes_backend.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @JsonBackReference
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((metadataId == null) ? 0 : metadataId.hashCode());
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        result = prime * result + ((memory == null) ? 0 : memory.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MemoryMetadata other = (MemoryMetadata) obj;
        if (metadataId == null) {
            if (other.metadataId != null)
                return false;
        } else if (!metadataId.equals(other.metadataId))
            return false;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        if (memory == null) {
            if (other.memory != null)
                return false;
        } else if (!memory.equals(other.memory))
            return false;
        return true;
    }

    

}
