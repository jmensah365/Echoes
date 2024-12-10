package com.mensahj.echoes_backend.DTOs;

import java.time.LocalDateTime;

public class LikesDTO {
    private LocalDateTime likedAt = LocalDateTime.now();

    public LocalDateTime getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(LocalDateTime likedAt) {
        this.likedAt = likedAt;
    }


    public LikesDTO() {
    }

    public LikesDTO(LocalDateTime likedAt) {
        this.likedAt = likedAt;
    }

    
}
