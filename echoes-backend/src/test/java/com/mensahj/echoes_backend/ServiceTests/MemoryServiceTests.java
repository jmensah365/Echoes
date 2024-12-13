package com.mensahj.echoes_backend.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.mensahj.echoes_backend.Models.Memories;
import com.mensahj.echoes_backend.Repositories.MemoryRepo;
import com.mensahj.echoes_backend.Services.MemoryService;

public class MemoryServiceTests {
    
    @Mock
    private MemoryRepo memoryRepo;

    @InjectMocks
    private MemoryService memoryService;

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
    void testGetMemoryById(){
        //Given: Create a Memories object with a specific ID
        Memories memory = new Memories();
        memory.setMemoryId(1);

        //When: Mock the repo's findById method
        when(memoryRepo.findById(1)).thenReturn(Optional.of(memory));

        // Call the service method to find the memory by its ID
        Memories result = memoryService.getMemoryById(1);

        // Assert: Check that the result does not equal null and the ID matches
        assertTrue(result != null);
        assertEquals(memory.getMemoryId(), result.getMemoryId());

        // Verify: Ensure that the repos findById method was called exactly once
        verify(memoryRepo, times(1)).findById(1);
    }

    @Test
    void testDeleteMemory(){
        // Given: Mock repository to simulate that the memory exists
        when(memoryRepo.existsById(1)).thenReturn(true);

         // When: Call the service method
        memoryService.deleteMemory(1);

        verify(memoryRepo,times(1)).deleteById(1);
    }

    @Test
    void testDeleteMemoryByTitle(){
        // Given: Mock repository to simulate that the memory exists
        when(memoryRepo.existsByTitle("Test")).thenReturn(true);

         // When: Call the service method
        memoryService.deleteMemoryByTitle("Test");

        verify(memoryRepo,times(1)).deleteByTitle("Test");
    }

    @Test
    void testDownloadMemoryFromFileSystem() throws IOException{
        // Arrange
        String fileName = "exampleTitle";
        String filePath = "/path/to/exampleTitle.jpg";
        byte[] expectedBytes = new byte[]{1, 2, 3};

        Memories mockMemory = new Memories();
        mockMemory.setFilePath(filePath);

        when(memoryRepo.findByTitle(fileName)).thenReturn(Optional.of(mockMemory));

        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            mockedFiles.when(() -> Files.readAllBytes(new File(filePath).toPath()))
                    .thenReturn(expectedBytes);

            // Act
            byte[] actualBytes = memoryService.downloadMemoryFromFileSystem(fileName);

            // Assert
            assertArrayEquals(expectedBytes, actualBytes);
            verify(memoryRepo, times(1)).findByTitle(fileName);

            // Verify the static method was called correctly
            mockedFiles.verify(() -> Files.readAllBytes(new File(filePath).toPath()));
        }
    }

    @Test
    void testDownloadMemoryFromFileSystem_filePathNotFound() throws IOException{
        // Arrange
        String fileName = "exampleTitle";
        String filePath = "/path/to/DNE.jpg";

        Memories mockMemory = new Memories();
        mockMemory.setFilePath(filePath);

        when(memoryRepo.findByTitle(fileName)).thenReturn(Optional.of(mockMemory));

        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            mockedFiles.when(() -> Files.readAllBytes(new File(filePath).toPath()))
                    .thenThrow(IOException.class);

            // Act
            assertThrows(IOException.class, () -> memoryService.downloadMemoryFromFileSystem(fileName));

            // Assert
            verify(memoryRepo, times(1)).findByTitle(fileName);

            // Verify the static method was called correctly
            mockedFiles.verify(() -> Files.readAllBytes(new File(filePath).toPath()));
        }
    }
}
