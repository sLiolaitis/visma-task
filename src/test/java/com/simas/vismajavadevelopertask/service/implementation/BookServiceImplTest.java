package com.simas.vismajavadevelopertask.service.implementation;

import com.google.gson.Gson;
import com.simas.vismajavadevelopertask.model.Book;
import com.simas.vismajavadevelopertask.model.Category;
import com.simas.vismajavadevelopertask.model.Language;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookServiceImplTest {

    @Test
    @DisplayName("Testing if the book is added")
    public void addBookTest (){
        BookServiceImpl bookServiceImpl = new BookServiceImpl();

        Book book = new Book("Dykumu gele", "Waris Dirie", Category.DRAMA,
                Language.LT,"2021-02-07","9786090103067",
                "9786090103067897451236", false);
        int initialCapacity = getAllLibrary().size();
        bookServiceImpl.addBook(book);
        int newCapacity = getAllLibrary().size();

        Assertions.assertEquals((initialCapacity+1),newCapacity,"Books successfully added");
        bookServiceImpl.deleteBook(book);
    }

    @Test
    @DisplayName("Testing if the right amount of books is returned")
    public void getAllBooksTest() {
        BookServiceImpl bookServiceImpl = new BookServiceImpl();
        int size1 = getAllLibrary().size();
        int size2 = bookServiceImpl.getAllBooks().size();
        Assertions.assertEquals(size1, size2);
    }

    @Test
    @DisplayName("Testing if the right book is returned by GUID")
    public void getBookByGuidTest (){
        BookServiceImpl bookService = new BookServiceImpl();
        Book book = new Book("Ausvico Tatuiruotojas", "Heather Morris",
                Category.DRAMA, Language.LT, "2019-06-13",
                "9786094792038", "9786094792038547896520", false);
        book.setGuid("777");
        bookService.addBook(book);
        Book bookTakenFromLibrary = bookService.getBookByGuid("777");
        Assertions.assertEquals(book, bookTakenFromLibrary);
    }

    @Test
    @DisplayName("Testing amount of filtered books")
    public void filterBooksByCriteriaTest(){
        BookServiceImpl bookService = new BookServiceImpl();

        List<Book> library = getAllLibrary();
        int booksWithWordHarryInTheName = 0;
        int booksWithCrimeCategory = 0;
        int booksInEnLanguage = 0;
        int booksWith99InIsbn = 0;
        int booksWithRowlingInTheAuthor = 0;
        int booksTaken = 0;
        for(Book b : library){
            if(b.getName().contains("Harry")) booksWithWordHarryInTheName++;
            if(b.getCategory()==Category.CRIME) booksWithCrimeCategory++;
            if(b.getLanguage()==Language.EN) booksInEnLanguage++;
            if(b.getIsbn().contains("99")) booksWith99InIsbn++;
            if(b.getAuthor().contains("Rowling")) booksWithRowlingInTheAuthor++;
            if(b.isTaken()) booksTaken++;
        }
        Assertions.assertEquals(booksWithWordHarryInTheName,
                bookService.filterBooksByCriteria("name","Harry").size());
        Assertions.assertEquals(booksWithCrimeCategory,
                bookService.filterBooksByCriteria("category","crime").size());
        Assertions.assertEquals(booksInEnLanguage,
                bookService.filterBooksByCriteria("language","en").size());
        Assertions.assertEquals(booksWith99InIsbn,
                bookService.filterBooksByCriteria("isbn","99").size());
        Assertions.assertEquals(booksWithRowlingInTheAuthor,
                bookService.filterBooksByCriteria("author","Rowling").size());
        Assertions.assertEquals(booksTaken,
                bookService.filterBooksByCriteria("taken","true").size());
    }

    @Test
    @DisplayName("Testing if book is deleted")
    public void deleteBookTest(){
        BookServiceImpl bookService = new BookServiceImpl();
        Book book = new Book("Dievu Miskas","Balys Sruoga",
                Category.DOCUMENTARY, Language.LT, "2013-01-01",
                "9789955236993","97899552369935485203", false);
        bookService.addBook(book);
        int sizeBeforeDelete = getAllLibrary().size();
        bookService.deleteBook(book);
        int sizeAfterDelete = getAllLibrary().size();

        Assertions.assertEquals((sizeBeforeDelete-1),sizeAfterDelete);
    }

    public List<Book> getAllLibrary (){
        List<Book> library = new ArrayList<>();
        try{
            Reader reader = new BufferedReader(new FileReader("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\library.json"));
            library = Arrays.asList(new Gson().fromJson(reader, Book[].class));
            reader.close();
        } catch (Exception e){
            System.out.println("Exception have been caught");
        }
        return library;
    }
}
