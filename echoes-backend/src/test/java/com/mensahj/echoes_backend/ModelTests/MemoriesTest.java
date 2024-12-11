package com.mensahj.echoes_backend.ModelTests;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;

import com.mensahj.echoes_backend.Models.Memories;

public class MemoriesTest {
    
    @Test
    void memoriesTest(){
        new BeanTester().testBean(Memories.class);
    }

    // @Test
    // void testEqualsMethod(){
    //     EqualsMethodTester tester = new EqualsMethodTester();
    //     tester.testEqualsMethod(Memories.class);
    // }

    // @Test
    // void testHashCodeMethod(){
    //     HashCodeMethodTester tester = new HashCodeMethodTester();
    //     tester.testHashCodeMethod(Memories.class);
    // }
}
