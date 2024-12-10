package com.mensahj.echoes_backend.DTOTests;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import com.mensahj.echoes_backend.DTOs.TagsDTO;

public class TagsDTOTest {
    
    @Test
    void tagsDTOTest(){
        new BeanTester().testBean(TagsDTO.class);
    }
}
