package com.simas.vismajavadevelopertask.controller;

import com.simas.vismajavadevelopertask.model.Book;
import com.simas.vismajavadevelopertask.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visma-task/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook (@RequestBody Book book){
        return new ResponseEntity<Book>(bookService.addBook(book), HttpStatus.OK);
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks (){
        return new ResponseEntity<List<Book>>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookByGuid (@PathVariable String id){
        return new ResponseEntity<Book>(bookService.getBookByGuid(id), HttpStatus.OK);
    }

    @GetMapping("/{filterBy}/{keyFraze}")
    public ResponseEntity<List<Book>> filterBooksByCriteria (@PathVariable String filterBy,
                                                             @PathVariable String keyFraze){
        return new ResponseEntity<List<Book>>(bookService.filterBooksByCriteria(filterBy, keyFraze), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook")
    public ResponseEntity<Book> deleteBook (@RequestBody Book book){
        return new ResponseEntity<Book>(bookService.deleteBook(book), HttpStatus.OK);
    }

}
