package org.model;

import lombok.Data;

@Data
public class ResponseUploadPhoto {
    private Integer server;
    private String photo;
    private String hash;
}
