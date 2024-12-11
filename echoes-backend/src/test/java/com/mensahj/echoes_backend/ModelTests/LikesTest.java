package com.mensahj.echoes_backend.ModelTests;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;

import com.mensahj.echoes_backend.Models.Likes;

public class LikesTest {
    
    @Test
    void likesTest(){
        new BeanTester().testBean(Likes.class);
    }

    @Test
    void testEqualsMethod(){
        EqualsMethodTester tester = new EqualsMethodTester();
        tester.testEqualsMethod(Likes.class);
    }

    @Test
    void testHashCodeMethod(){
        HashCodeMethodTester tester = new HashCodeMethodTester();
        tester.testHashCodeMethod(Likes.class);
    }
}
