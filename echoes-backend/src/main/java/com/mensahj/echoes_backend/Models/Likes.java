package com.mensahj.echoes_backend.Models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "likes")
public class Likes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer likes_id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime likedAt = LocalDateTime.now();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id")
    private Memories memory;

    public Integer getLikes_id() {
        return likes_id;
    }

    public void setLikes_id(Integer likes_id) {
        this.likes_id = likes_id;
    }

    public LocalDateTime getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(LocalDateTime likedAt) {
        this.likedAt = likedAt;
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
        result = prime * result + ((likes_id == null) ? 0 : likes_id.hashCode());
        result = prime * result + ((likedAt == null) ? 0 : likedAt.hashCode());
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
        Likes other = (Likes) obj;
        if (likes_id == null) {
            if (other.likes_id != null)
                return false;
        } else if (!likes_id.equals(other.likes_id))
            return false;
        if (likedAt == null) {
            if (other.likedAt != null)
                return false;
        } else if (!likedAt.equals(other.likedAt))
            return false;
        if (memory == null) {
            if (other.memory != null)
                return false;
        } else if (!memory.equals(other.memory))
            return false;
        return true;
    }

    




}
