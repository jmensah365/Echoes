package com.mensahj.echoes_backend.DTOTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import com.mensahj.echoes_backend.DTOs.AlbumsDTO;

public class AlbumsDTOTest {
    
    @Test
    void albumsDTOTest(){
        new BeanTester().testBean(AlbumsDTO.class);
    }

    @Test
    void testAlbumsConstructor(){
        int id = 1;
        String title = "Test title";
        String description = "Test description";

        AlbumsDTO album = new AlbumsDTO(id, title, description);

        assertEquals(id, album.getId());
        assertEquals(description, album.getDescription());
        assertEquals(title, album.getTitle());
    }
}
