package com.mensahj.echoes_backend.DTOs;

public class AlbumsDTO {
    private Integer id;
    private String title;
    private String description;


    
    public AlbumsDTO() {
    }

    
    public AlbumsDTO(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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

    
}
