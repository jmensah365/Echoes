package com.mensahj.echoes_backend.ServiceTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.mensahj.echoes_backend.Models.MemoryMetadata;
import com.mensahj.echoes_backend.Services.MetadataExtractor;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MetadataExtractorTest {

    @Mock
    private ImageMetadataReader imageMetadataReader;

    @InjectMocks
    private MetadataExtractor metadataExtractor;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testExtractPhotoMetadata() throws Exception {
        //mock the multipartfile 
        MockMultipartFile photoFile = new MockMultipartFile("photo", "photo.jpeg", "image/jpeg", new byte[]{});

        //mock the metadata, directory, and tag classes
        Metadata mockMetadata = mock(Metadata.class);
        Directory mockDirectory = mock(Directory.class);
        Tag tag1 = mock(Tag.class);
        Tag tag2 = mock(Tag.class);

        when(mockDirectory.getTags()).thenReturn(List.of(tag1,tag2));
        when(mockMetadata.getDirectories()).thenReturn(List.of(mockDirectory));

        when(tag1.getTagName()).thenReturn("Tag 1 Name");
        when(tag1.getDescription()).thenReturn("Description 1");
        when(tag2.getTagName()).thenReturn("Tag 2 Name");
        when(tag2.getDescription()).thenReturn("Description 2");

        try(MockedStatic<ImageMetadataReader> mockedReader = mockStatic(ImageMetadataReader.class)){
            mockedReader.when(() -> ImageMetadataReader.readMetadata(any(InputStream.class))).thenReturn(mockMetadata);

            List<MemoryMetadata> memoryMetadata = MetadataExtractor.extractPhotoMetadata(photoFile);


            assertEquals(2, memoryMetadata.size());
            assertEquals(tag1.getTagName(), memoryMetadata.get(0).getKey());
            assertEquals(tag1.getDescription(), memoryMetadata.get(0).getValue());
            assertEquals(tag2.getTagName(), memoryMetadata.get(1).getKey());
            assertEquals(tag2.getDescription(), memoryMetadata.get(1).getValue());
        }
    }
}