package com.simas.vismajavadevelopertask.service.implementation;

import com.google.gson.Gson;
import com.simas.vismajavadevelopertask.model.Book;
import com.simas.vismajavadevelopertask.model.Category;
import com.simas.vismajavadevelopertask.service.BookService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Override
    public Book addBook(Book book) {
        List<Book> booksList = new ArrayList<>();
        booksList.add(book);
        List<Book> library = readFromLibrary();
        booksList.addAll(library);
        try {
            FileWriter fileWriter = new FileWriter("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\library.json", false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Set<Book> booksSet = new HashSet<>(booksList);
            booksList = new ArrayList<>(booksSet);
            bufferedWriter.write(new Gson().toJson(booksList));
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred in writing!");
        }

        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> allBooksList = new ArrayList<>();
        try {
            Reader reader = new BufferedReader(new FileReader("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\library.json"));
            Gson gson = new Gson();
            Book[] allBooksArray = gson.fromJson(reader, Book[].class);
            allBooksList = Arrays.asList(allBooksArray);
        } catch (Exception e) {
            System.out.println("Reading has failed");
        }
        return allBooksList;
    }

    @Override
    public Book getBookByGuid(String guid) {
        Gson gson = new Gson();
        Reader reader = null;
        Book[] booksArray = null;
        List<Book> booksList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\library.json"));
            booksArray = gson.fromJson(reader, Book[].class);
            booksList = Arrays.asList(booksArray);
        } catch (Exception e) {
            System.out.println("Exception while reading");
        }

        for (Book b : booksList) {
            if (b.getGuid().equals(guid)) {
                return b;
            }
        }
        return null;
    }

    public List<Book> readFromLibrary() {
        Reader reader = null;
        Book[] array = null;
        List<Book> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\library.json"));
            Gson gson = new Gson();
            array = gson.fromJson(reader, Book[].class);
            list = Arrays.asList(array);
            reader.close();
        } catch (Exception e) {
            System.out.println("Exception caught");
        }
        return list;
    }

    @Override
    public List<Book> filterBooksByCriteria(String filterBy, String keyPhrase) {
        List<Book> library = readFromLibrary();
        List<Book> filtered = new ArrayList<>();

        filterBy = filterBy.toLowerCase(Locale.ROOT);
        filterBy = filterBy.replaceAll(" ", "");

        keyPhrase = keyPhrase.toLowerCase(Locale.ROOT).replaceAll(" ", "");

        switch (filterBy) {
            case "author":
                for (Book b : library) {
                    String temp = b.getAuthor().toLowerCase(Locale.ROOT).replaceAll(" ", "");
                    if ((temp.equals(keyPhrase)) || (temp.contains(keyPhrase))) {
                        filtered.add(b);
                    }
                }
                break;
            case "category":
                for(Book b : library){
                    String category = b.getCategory().toString().toLowerCase(Locale.ROOT);
                    if((category.equals(keyPhrase))||(category.contains(keyPhrase))){
                        filtered.add(b);
                    }
                }
                break;
            case "language":
                for(Book b : library){
                    String language = b.getLanguage().toString().toLowerCase(Locale.ROOT);
                    if(language.equals(keyPhrase)){
                        filtered.add(b);
                    }
                }
                break;
            case "isbn":
                for(Book b : library){
                    String isbn = b.getIsbn().toString().toLowerCase(Locale.ROOT);
                    if((keyPhrase.equals(isbn))||(isbn.contains(keyPhrase))){
                        filtered.add(b);
                    }
                }
                break;
            case "name":
                for(Book b : library){
                    String name = b.getName().toLowerCase(Locale.ROOT).replaceAll(" ","");
                    if((name.equals(keyPhrase))||(name.contains(keyPhrase))){
                        filtered.add(b);
                    }
                }
                break;
            case "taken":
                for(Book b : library){
                    String taken = String.valueOf(b.isTaken());
                    if(taken.equals(keyPhrase)){
                        filtered.add(b);
                    }
                }
                break;
            default:
                return null;
        }

        return filtered;
    }

    @Override
    public Book deleteBook(Book book) {
        List<Book> library = readFromLibrary();
        List<Book> newList = new ArrayList<>();
        for(Book b : library){
            if(!b.equals(book)){
                newList.add(b);
            }
        }

        try{
            Writer writer = new BufferedWriter(new FileWriter("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\library.json",false));
            Gson gson = new Gson();
            writer.write(gson.toJson(newList));
            writer.close();
        } catch (Exception e){
            System.out.println("Exception while writing");
        }

        return book;
    }
}
