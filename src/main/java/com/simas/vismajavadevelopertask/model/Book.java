package com.simas.vismajavadevelopertask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String name;
    private String author;
    private Category category;
    private Language language;
    private String publicationDate;
    private String isbn;
    private String guid;
    private boolean taken;

}
