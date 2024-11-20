package com.mensahj.echoes_backend.Models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "memories")
public class Memories {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memoryId;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @Column(nullable = false)
    private String filePath;

    @Column
    private String fileType;

    @Column(nullable = false, updatable = false)
    private final LocalDateTime uploadedAt = LocalDateTime.now();

    //Many-to-Many relationship
    @ManyToMany(mappedBy = "memories")
    private List<Albums> albums;

    @ManyToMany(mappedBy = "memories")
    private List<Tags> tags;

    //One-to-Many relationship
    @OneToMany(mappedBy = "memory")
    private List<MemoryMetadata> memoryMetadata;

    @OneToMany(mappedBy = "memory")
    private List<Likes> likes;

    

    public Memories() {
    }

    public Integer getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(Integer memoryId) {
        this.memoryId = memoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public List<Albums> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Albums> albums) {
        this.albums = albums;
    }

    public List<MemoryMetadata> getMemoryMetadata() {
        return memoryMetadata;
    }

    public void setMemoryMetadata(List<MemoryMetadata> memoryMetadata) {
        this.memoryMetadata = memoryMetadata;
    }

}
