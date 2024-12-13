package com.mensahj.echoes_backend.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mensahj.echoes_backend.DTOs.LikesDTO;
import com.mensahj.echoes_backend.Models.Likes;
import com.mensahj.echoes_backend.Models.Memories;
import com.mensahj.echoes_backend.Repositories.LikesRepo;
import com.mensahj.echoes_backend.Services.LikesService;

public class LikesServiceTests {
    
    @Mock
    private LikesRepo likesRepo;

    @InjectMocks
    private LikesService likesService;

    private AutoCloseable closeable;

    @BeforeEach
    void setup(){
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void teardown() throws Exception{
        closeable.close();
    }

    @Test
    void testGetAllLikes(){
        // Given: Prepare a list of Likes objects that the repository is expected to return
        List<Likes> expectedLikes = Arrays.asList(new Likes(), new Likes());

         //When: Mock the repositories findAll method to return the above list
        when(likesRepo.findAll()).thenReturn(expectedLikes);

        //Call the service method to get the list of Likes
        List<Likes> actualLikes = likesService.getAllLikes();

        //Assert: assert the expected and actual match
        assertEquals(expectedLikes, actualLikes);

        //Verify: Ensure that the repos method was called exactly once
        verify(likesRepo, times(1)).findAll();
    }

    @Test
    void testLikeAMemory(){
        //Given: Create a Likes, LikesDTO, and  Memory object
        Memories memory = new Memories();
        memory.setTitle("Title");

        LikesDTO likeDTO = new LikesDTO();
        likeDTO.setLikedAt(LocalDateTime.now());

        Likes expectedLike = new Likes();
        expectedLike.setLikedAt(likeDTO.getLikedAt());
        expectedLike.setMemory(memory);


        //When: Mock the repositories save method to return created like
        when(likesRepo.save(any(Likes.class))).thenReturn(expectedLike);

         // Call the service method to like a memory
        Likes newLike = likesService.likeAMemory(likeDTO, memory);

        assertEquals(expectedLike.getLikedAt(), newLike.getLikedAt());
        assertEquals(expectedLike.getMemory().getTitle(), newLike.getMemory().getTitle());

        verify(likesRepo, times(1)).save(any(Likes.class));

    }

    @Test
    void testDeleteALike(){
    // Given: Mock repository to simulate that the Like exists
    when(likesRepo.existsById(1)).thenReturn(true);

    // When: Call the service method
    likesService.deleteALike(1);

    // Then: Verify that the deleteById method was called
    verify(likesRepo, times(1)).deleteById(1);
    }
}
