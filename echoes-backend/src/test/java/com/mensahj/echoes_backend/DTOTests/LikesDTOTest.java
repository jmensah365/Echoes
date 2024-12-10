package com.mensahj.echoes_backend.DTOTests;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import com.mensahj.echoes_backend.DTOs.LikesDTO;

public class LikesDTOTest {
    
    @Test
    void testLikesDTO(){
        new BeanTester().testBean(LikesDTO.class);
    }
}
