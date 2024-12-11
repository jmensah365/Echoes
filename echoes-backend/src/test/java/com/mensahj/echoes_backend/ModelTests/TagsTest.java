package com.mensahj.echoes_backend.ModelTests;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;

import com.mensahj.echoes_backend.Models.Tags;

public class TagsTest {
    
    @Test
    void tagsTest(){
        new BeanTester().testBean(Tags.class);
    }

    @Test
    void testEqualsMethod(){
        EqualsMethodTester tester = new EqualsMethodTester();
        tester.testEqualsMethod(Tags.class);
    }

    @Test
    void testHashCodeMethod(){
        HashCodeMethodTester tester = new HashCodeMethodTester();
        tester.testHashCodeMethod(Tags.class);
    }
}
