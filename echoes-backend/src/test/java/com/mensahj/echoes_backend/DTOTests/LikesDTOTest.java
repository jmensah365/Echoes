package com.mensahj.echoes_backend.DTOTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import com.mensahj.echoes_backend.DTOs.LikesDTO;

public class LikesDTOTest {
    
    @Test
    void testLikesDTO(){
        new BeanTester().testBean(LikesDTO.class);
    }

    @Test
    void testLikesDTOConstructor(){
        LocalDateTime likedAt = LocalDateTime.now();

        LikesDTO like = new LikesDTO(likedAt);

        assertEquals(likedAt, like.getLikedAt());
    }
}
