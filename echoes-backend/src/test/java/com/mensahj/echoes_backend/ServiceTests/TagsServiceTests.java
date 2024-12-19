package com.mensahj.echoes_backend.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mensahj.echoes_backend.DTOs.TagsDTO;
import com.mensahj.echoes_backend.Models.Memories;
import com.mensahj.echoes_backend.Models.Tags;
import com.mensahj.echoes_backend.Repositories.MemoryRepo;
import com.mensahj.echoes_backend.Repositories.TagsRepo;
import com.mensahj.echoes_backend.Services.TagsService;

public class TagsServiceTests {
    
    @Mock
    private TagsRepo tagsRepo;
    @Mock
    private MemoryRepo memoryRepo;

    @InjectMocks
    private TagsService tagsService;

    AutoCloseable closeable;

    @BeforeEach
    void setup(){
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void teardown() throws Exception{
        closeable.close();
    }

    @Test
    void testGetAllTags(){
        List<Tags> expectedTags = Arrays.asList(new Tags(), new Tags());

        when(tagsRepo.findAll()).thenReturn(expectedTags);

        List<Tags> actualTags = tagsService.getAllTags();

        assertEquals(expectedTags, actualTags);

        verify(tagsRepo, times(1)).findAll();
    }

    @Test
    void testGetTagById(){
        Tags expectedTag = new Tags();
        expectedTag.setTagId(1);

        when(tagsRepo.findById(1)).thenReturn(Optional.of(expectedTag));

        Tags actualTag = tagsService.getTagById(1);

        assertEquals(expectedTag, actualTag);

        verify(tagsRepo, times(1)).findById(1);
    }

    @Test
    void testCreateTag(){

        Tags tag = new Tags();
        tag.setName("Test Tag");


        TagsDTO tagDTO = new TagsDTO();
        tagDTO.setName("Test Tag");

        when(tagsRepo.save(any(Tags.class))).thenReturn(tag);

        Tags actual = tagsService.createTag(tagDTO);

        assertEquals(tag, actual);
        assertEquals(tag.getName(), actual.getName());

        verify(tagsRepo, times(1)).save(any(Tags.class));
    }

    @Test
    void testAddTagToMemory(){
        Tags tag = new Tags();
        tag.setMemories(new HashSet<>());

        Memories memory = new Memories();
        memory.setTags(new HashSet<>());

        when(tagsRepo.save(any(Tags.class))).thenReturn(tag);
        when(memoryRepo.save(any(Memories.class))).thenReturn(memory);

        tagsService.addTagToMemory(tag, memory);

        //assertTrue(tag.getMemories().contains(memory));
        assertTrue(memory.getTags().contains(tag));

        verify(tagsRepo, times(1)).save(any(Tags.class));
    }

    @Test
    void testModifyTag(){
        Tags tag = new Tags();
        tag.setTagId(1);
        tag.setName("Test Tag");

        when(tagsRepo.existsById(1)).thenReturn(true);
        when(tagsRepo.save(any(Tags.class))).thenReturn(tag);

        Tags actual = tagsService.modifyTag(1, tag);

        assertEquals(tag, actual);
        assertEquals(tag.getName(), actual.getName());

        verify(tagsRepo, times(1)).save(any(Tags.class));
    }

    @Test
    void testDeleteTagById(){
        Tags tag = new Tags();
        tag.setTagId(1);

        when(tagsRepo.existsById(1)).thenReturn(true);

        tagsService.deleteTagById(1);

        verify(tagsRepo, times(1)).deleteById(1);
    }
}
