package com.mensahj.echoes_backend.DTOTests;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import com.mensahj.echoes_backend.DTOs.AlbumsDTO;

public class AlbumsDTOTest {
    
    @Test
    void albumsDTOTest(){
        new BeanTester().testBean(AlbumsDTO.class);
    }
}
