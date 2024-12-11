package com.mensahj.echoes_backend.ModelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;

import com.mensahj.echoes_backend.DTOs.AlbumsDTO;
import com.mensahj.echoes_backend.Models.Albums;

public class AlbumsTest {
    
    @Test
    void albumsTest(){
        new BeanTester().testBean(Albums.class);
    }

    // @Test
    // void testEqualsMethod(){
    //     EqualsMethodTester tester = new EqualsMethodTester();
    //     tester.testEqualsMethod(Albums.class);
    // }

    // @Test
    // void testHashCodeMethod(){
    //     HashCodeMethodTester tester = new HashCodeMethodTester();
    //     tester.testHashCodeMethod(Albums.class);
    // }

}
