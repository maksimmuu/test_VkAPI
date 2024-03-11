package org.model;

import lombok.Data;

import java.util.List;

@Data
public class ResponseSavePhoto {
    private List<SavedPhoto> response;
}
