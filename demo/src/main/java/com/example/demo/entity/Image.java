package com.example.demo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * image
 * @author 
 */
@Data
public class Image implements Serializable {
    private String image;

    private String text;
    
    private String uploader;

    private static final long serialVersionUID = 1L;
}