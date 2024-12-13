package com.mensahj.echoes_backend.ServiceTests;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mensahj.echoes_backend.Repositories.AlbumsRepo;
import com.mensahj.echoes_backend.Services.AlbumsService;
import com.mensahj.echoes_backend.DTOs.AlbumsDTO;
import com.mensahj.echoes_backend.Models.Albums;
import com.mensahj.echoes_backend.Models.Memories;

public class AlbumsServiceTests {
    
    @Mock
    private AlbumsRepo albumsRepo;

    @InjectMocks
    private AlbumsService albumsService;

    private AutoCloseable closeable;

    @BeforeEach
    void setup(){
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    void testGetAllAlbums(){
        // Given: Prepare a list of Album objects that the repository is expected to return
        List<Albums> expectedAlbums = Arrays.asList(new Albums(), new Albums());

        //When: Mock the repositories findAll method to return the above list
        when(albumsRepo.findAll()).thenReturn(expectedAlbums);

        // Call the service method to get the list of albums
        List<Albums> actualAlbums = albumsService.getAllAlbums();

        // Assert: Check that the actual list mathces the expected list
        assertEquals(expectedAlbums, actualAlbums);

        //Verify: Ensure that the repos method was called exactly once
        verify(albumsRepo, times(1)).findAll();
    }

    @Test
    void testGetAlbumById(){
        //Given: Create a Albums object with a specific ID
        Albums album = new Albums();
        album.setAlbum_id(1);

        //When: Mock the repo's findById method
        when(albumsRepo.findById(1)).thenReturn(Optional.of(album));

        // Call the service method to find the album by its ID
        Albums result = albumsService.getAlbumById(1);

        // Assert: Check that the result does not equal null and the ID matches
        assertTrue(result != null);
        assertEquals(result.getAlbum_id(), 1);

        // Verify: Ensure that the repos findById method was called exactly once
        verify(albumsRepo, times(1)).findById(1);
    }

    @Test
    void testCreateAlbum(){
        // Given: Create a new Albums and AlbumsDTO object
        Albums album = new Albums();
        album.setAlbum_id(1);
        album.setTitle("Test title");
        album.setDescription("Test description");

        AlbumsDTO albumDTO = new AlbumsDTO();
        albumDTO.setId(1);
        albumDTO.setTitle("Test title");
        albumDTO.setDescription("Test description");

        //When: Mock the repo's save method to return the same Albums object
        when(albumsRepo.save(any(Albums.class))).thenReturn(album);

        // Call the service method to create an Album
        Albums createdAlbum = albumsService.createAlbum(albumDTO);

        //Assert: Check that the create album matches the original Album
        assertEquals(album.getAlbum_id(), createdAlbum.getAlbum_id());
        assertEquals(album.getTitle(), createdAlbum.getTitle());
        assertEquals(album.getDescription(), createdAlbum.getDescription());

        //Verify: Ensure that the repo's save method was called exactly once
        verify(albumsRepo, times(1)).save(any(Albums.class));
    }

    @Test
    void testUploadMemoryIntoAlbum(){
        //Given: create a new Album and new Memory object
        Albums album = new Albums();
        Memories memory = new Memories();

        // Initialize their memories and albums fields with empty array lists
        album.setMemories(new ArrayList<>());
        memory.setAlbums(new ArrayList<>());

        // Call the service method to upload a memory into an album
        albumsService.uploadMemoryIntoAlbum(album, memory);

        //Assert: assert the memory has been added to the album and vice-versa
        assertTrue(album.getMemories().contains(memory));
        assertTrue(memory.getAlbums().contains(album));

        //Verify: verify the save method has been called exactly once
        verify(albumsRepo, times(1)).save(album);
    }

    @Test
    void testUploadMemoryIntoAlbum_MemoryAlreadyInAlbum(){
        //Given: create a new Album and Memories object
        Albums album = new Albums();
        Memories memory = new Memories();

        album.setMemories(new ArrayList<>());
        album.getMemories().add(memory); // add memory to album

        //Expect an error to be thrown
        Error error = assertThrows(Error.class, () -> {
            albumsService.uploadMemoryIntoAlbum(album, memory);
        });

        assertEquals("Memory has already been uploaded to this album", error.getMessage());

        verify(albumsRepo, never()).save(album);
    }

    @Test
    void testDeleteMemoryFromAlbum(){
        //Given: create a new Album and new Memory object
        Albums album = new Albums();
        Memories memory = new Memories();

        // Initialize their memories and albums fields with empty array lists
        album.setMemories(new ArrayList<>());
        memory.setAlbums(new ArrayList<>());

        // Call the service method to upload a memory into an album
        albumsService.deleteMemoryFromAlbum(album, memory);

        //Assert: assert the memory has been added to the album and vice-versa
        assertFalse(album.getMemories().contains(memory));
        assertFalse(memory.getAlbums().contains(album));

        //Verify: verify the save method has been called exactly once
        verify(albumsRepo, times(1)).save(album);
    }

    @Test
    void testDeleteAlbumById(){
        Albums album = new Albums();
        album.setAlbum_id(1);

        albumsService.deleteAlbumById(1);

        verify(albumsRepo, times(1)).deleteById(1);
    }

    @Test
    void testDeleteAlbumByTitle(){
        Albums album = new Albums();
        album.setTitle("Title");

        albumsService.deleteAlbumByTitle("Title");

        verify(albumsRepo, times(1)).deleteByTitle("Title");
    }
}
