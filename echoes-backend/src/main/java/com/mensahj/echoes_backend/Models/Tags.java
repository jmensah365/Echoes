package com.mensahj.echoes_backend.Models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tags")
public class Tags {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tagId;


    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name cannot exceed 255 characters")
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    @JoinTable(
        name = "memory_tags",
        joinColumns = @JoinColumn(name = "tag_id"),
        inverseJoinColumns = @JoinColumn(name = "memory_id")
    )
    private Set<Memories> memories = new HashSet<>();


    public Integer getTagId() {
        return tagId;
    }


    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Set<Memories> getMemories() {
        return memories;
    }


    public void setMemories(Set<Memories> memories) {
        this.memories = memories;
    }

    

}
