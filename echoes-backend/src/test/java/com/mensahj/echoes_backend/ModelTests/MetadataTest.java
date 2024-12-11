package com.mensahj.echoes_backend.ModelTests;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;

import com.mensahj.echoes_backend.Models.MemoryMetadata;

public class MetadataTest {
    
    @Test
    void metadataTest(){
        new BeanTester().testBean(MemoryMetadata.class);
    }

    @Test
    void testEqualsMethod(){
        EqualsMethodTester tester = new EqualsMethodTester();
        tester.testEqualsMethod(MemoryMetadata.class);
    }

    @Test
    void testHashCodeMethod(){
        HashCodeMethodTester tester = new HashCodeMethodTester();
        tester.testHashCodeMethod(MemoryMetadata.class);
    }
}
