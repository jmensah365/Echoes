package com.mensahj.echoes_backend.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonBackReference
    @ManyToMany(mappedBy = "memories")
    private List<Albums> albums = new ArrayList<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "memories")
    private Set<Tags> tags = new HashSet<>();

    //One-to-Many relationship
    @JsonManagedReference
    @OneToMany(mappedBy = "memory")
    private List<MemoryMetadata> memoryMetadata;

    @JsonManagedReference
    @OneToMany(mappedBy = "memory")
    private List<Likes> likes = new ArrayList<>();

    

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


    public Set<Tags> getTags() {
        return tags;
    }

    public void setTags(Set<Tags> tags) {
        this.tags = tags;
    }

    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((memoryId == null) ? 0 : memoryId.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
        result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
        result = prime * result + ((uploadedAt == null) ? 0 : uploadedAt.hashCode());
        result = prime * result + ((albums == null) ? 0 : albums.hashCode());
        result = prime * result + ((tags == null) ? 0 : tags.hashCode());
        result = prime * result + ((memoryMetadata == null) ? 0 : memoryMetadata.hashCode());
        result = prime * result + ((likes == null) ? 0 : likes.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Memories other = (Memories) obj;
        if (memoryId == null) {
            if (other.memoryId != null)
                return false;
        } else if (!memoryId.equals(other.memoryId))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (filePath == null) {
            if (other.filePath != null)
                return false;
        } else if (!filePath.equals(other.filePath))
            return false;
        if (fileType == null) {
            if (other.fileType != null)
                return false;
        } else if (!fileType.equals(other.fileType))
            return false;
        if (uploadedAt == null) {
            if (other.uploadedAt != null)
                return false;
        } else if (!uploadedAt.equals(other.uploadedAt))
            return false;
        if (albums == null) {
            if (other.albums != null)
                return false;
        } else if (!albums.equals(other.albums))
            return false;
        if (tags == null) {
            if (other.tags != null)
                return false;
        } else if (!tags.equals(other.tags))
            return false;
        if (memoryMetadata == null) {
            if (other.memoryMetadata != null)
                return false;
        } else if (!memoryMetadata.equals(other.memoryMetadata))
            return false;
        if (likes == null) {
            if (other.likes != null)
                return false;
        } else if (!likes.equals(other.likes))
            return false;
        return true;
    }

    

}
