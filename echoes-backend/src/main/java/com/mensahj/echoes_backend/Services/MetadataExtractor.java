package com.mensahj.echoes_backend.Services;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.mensahj.echoes_backend.Models.MemoryMetadata;

public class MetadataExtractor {
    
    public static List<MemoryMetadata> extractPhotoMetadata(MultipartFile photoFile) {
        List<MemoryMetadata> metadataList = new ArrayList<>();

        try (InputStream inputStream = photoFile.getInputStream()) {
            Metadata metadata = ImageMetadataReader.readMetadata(inputStream);

            List<String> allowedTags = List.of("");

            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    MemoryMetadata memoryMetadata = new MemoryMetadata();
                    memoryMetadata.setKey(tag.getTagName());
                        // Sanitize value to remove null bytes
                    String sanitizedDescription = tag.getDescription() != null ? tag.getDescription().replace("\u0000", "") : null;
                    memoryMetadata.setValue(sanitizedDescription);
                    metadataList.add(memoryMetadata);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return metadataList;
    }
}
