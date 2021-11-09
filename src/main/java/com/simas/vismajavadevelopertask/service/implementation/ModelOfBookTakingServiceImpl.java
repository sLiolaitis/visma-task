package com.simas.vismajavadevelopertask.service.implementation;

import com.google.gson.Gson;
import com.simas.vismajavadevelopertask.model.Book;
import com.simas.vismajavadevelopertask.model.Member;
import com.simas.vismajavadevelopertask.model.ModelOfBookTaking;
import com.simas.vismajavadevelopertask.service.ModelOfBookTakingService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ModelOfBookTakingServiceImpl implements ModelOfBookTakingService {


    @Override
    public List<ModelOfBookTaking> takeBooks(ModelOfBookTaking... modelOfBookTakings) {

        if (modelOfBookTakings.length > 3) return null;

        List<ModelOfBookTaking> listOfModels = new ArrayList<>();
        List<Book> listOfBooks = new ArrayList<>();
        List<Member> listOfMembers = new ArrayList<>();
        List<Book> newListOfBooks = new ArrayList<>();

        try {
            Reader booksReader = new BufferedReader(new FileReader("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\library.json"));
            Reader membersReader = new BufferedReader(new FileReader("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\members.json"));
            listOfBooks = Arrays.asList(new Gson().fromJson(booksReader, Book[].class));
            listOfMembers = Arrays.asList(new Gson().fromJson(membersReader, Member[].class));
            booksReader.close();
            membersReader.close();
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        for (ModelOfBookTaking model : modelOfBookTakings) {
            if (model.getDays() < 61) {
                for (Member member : listOfMembers) {
                    if (model.getMemberId() == member.getMemberId()) {
                        for (Book book : listOfBooks) {
                            if ((model.getBookGuid().equals(book.getGuid()))&&(!book.isTaken())) {
                                listOfModels.add(model);
                            }
                        }
                    }
                }
            }
        }

        List<ModelOfBookTaking> currentBookTakingLog = new ArrayList<>();
        try{
            Reader reader = new BufferedReader(new FileReader("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\bookTakingLog.json"));
            currentBookTakingLog = Arrays.asList(new Gson().fromJson(reader, ModelOfBookTaking[].class));
            reader.close();
        }catch (Exception e){
            System.out.println("Reading exception");
        }

        try {
            Writer writer = new BufferedWriter(new FileWriter("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\bookTakingLog.json", false));
            listOfModels.addAll(currentBookTakingLog);
            writer.write(new Gson().toJson(listOfModels));
            writer.close();
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        List<String> guidsList = new ArrayList<>();

        for (Book book : listOfBooks) {
            for (ModelOfBookTaking model : listOfModels) {
                if(model.getBookGuid().equals(book.getGuid())){
                    book.setTaken(true);
                    newListOfBooks.add(book);
                    guidsList.add(book.getGuid());
                }
            }
        }

        for(Book book : listOfBooks){
            if(!guidsList.contains(book.getGuid())){
                newListOfBooks.add(book);
            }
        }

        try{
            Writer writer = new BufferedWriter(new FileWriter("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\library.json",false));
            writer.write(new Gson().toJson(newListOfBooks));
            writer.close();
        } catch (Exception e){
            System.out.println("Error while writing");
        }

        return listOfModels;
    }
}
