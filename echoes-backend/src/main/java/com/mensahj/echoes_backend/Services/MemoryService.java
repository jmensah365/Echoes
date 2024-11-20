package com.mensahj.echoes_backend.Services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mensahj.echoes_backend.Models.Memories;
import com.mensahj.echoes_backend.Models.MemoryMetadata;
import com.mensahj.echoes_backend.Repositories.MemoryRepo;
import com.mensahj.echoes_backend.Repositories.MetadataRepo;

import jakarta.transaction.Transactional;

@Service
public class MemoryService {
    @Autowired
    private MemoryRepo memoryRepo;

    @Autowired
    private MetadataRepo metadataRepo;

    private final String FOLDER_PATH = "/Users/mensahj/Desktop/Echoes/";

    public List<Memories> getAllMemories(){
        return memoryRepo.findAll();
    }

    public Memories getMemoryById(int id){
        return memoryRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Memory not found."));
    }

    public Optional<Memories> getMemoryByTitle(String title){
        return memoryRepo.findByTitle(title);
    }


    public String uploadMemoryToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH+file.getOriginalFilename();

        Memories memory = new Memories();
        memory.setTitle(file.getOriginalFilename());
        memory.setDescription("Test");
        memory.setFilePath(filePath);
        memory.setFileType(file.getContentType());
        memoryRepo.save(memory);

        List<MemoryMetadata> metadataList = MetadataExtractor.extractPhotoMetadata(file);

        for (MemoryMetadata metadata : metadataList) {
            if(metadata.getKey() != null && metadata.getValue() != null) {
                metadata.setMemory(memory);
                metadataRepo.save(metadata);
            }
        }

        file.transferTo(new File(filePath));

        {
            return "file uploaded successfully: " + filePath;
        }
    }

    public byte[] downloadMemoryFromFileSystem(String fileName) throws IOException{
        Optional<Memories> memory = memoryRepo.findByTitle(fileName);
        String filePath = memory.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }



    public void deleteMemory(int id) {
        if (!memoryRepo.existsById(id)) {
            throw new NoSuchElementException("Memory not found.");
        }
        memoryRepo.deleteById(id);
    }

    @Transactional
    public void deleteMemoryByTitle(String title) {
        if (!memoryRepo.existsByTitle(title)) {
            throw new NoSuchElementException("Memory not found.");
        }
        memoryRepo.deleteByTitle(title);
    }
}
