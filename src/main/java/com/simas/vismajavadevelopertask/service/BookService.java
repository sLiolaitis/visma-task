package com.simas.vismajavadevelopertask.service;

import com.simas.vismajavadevelopertask.model.Book;

import java.util.List;

public interface BookService {

    Book addBook (Book book);
    List<Book> getAllBooks();
    Book getBookByGuid (String guid);
    List<Book> filterBooksByCriteria (String filterBy, String keyFraze);
    Book deleteBook (Book book);

}
