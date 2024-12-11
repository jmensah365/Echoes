package com.mensahj.echoes_backend.DTOTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import com.mensahj.echoes_backend.DTOs.TagsDTO;

public class TagsDTOTest {
    
    @Test
    void tagsDTOTest(){
        new BeanTester().testBean(TagsDTO.class);
    }


    @Test
    void testTagsDTOConstructor(){
        String name = "test name";

        TagsDTO tag = new TagsDTO(name);

        assertEquals(name, tag.getName());
    }
}
