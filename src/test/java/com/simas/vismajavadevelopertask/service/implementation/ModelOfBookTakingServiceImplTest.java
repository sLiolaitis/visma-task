package com.simas.vismajavadevelopertask.service.implementation;

import com.google.gson.Gson;
import com.simas.vismajavadevelopertask.model.ModelOfBookTaking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelOfBookTakingServiceImplTest {

    @Test
    @DisplayName("Testing if taking more than 3 books is really not allowed")
    public void takeBooksTest1 (){
        ModelOfBookTakingServiceImpl modelOfBookTakingService = new ModelOfBookTakingServiceImpl();

        Assertions.assertEquals(null, modelOfBookTakingService.takeBooks(
                new ModelOfBookTaking(201458952,"97860901474439850214",21)
                , new ModelOfBookTaking(369852014,"125847159632",22)
                , new ModelOfBookTaking(369852014,"97860948025913001425",23)
                , new ModelOfBookTaking(369852014,"0003000300030003",24)));
    }

    @Test
    @DisplayName("Testing if it is really not allowed to take book for more than 60 days")
    public void takeBooksTest2(){
        ModelOfBookTakingServiceImpl modelOfBookTakingService = new ModelOfBookTakingServiceImpl();
        int initialSize = getBookTakingLog().size();
        modelOfBookTakingService.takeBooks(
                new ModelOfBookTaking(987521458, "125847159632", 70));
        int newSize = getBookTakingLog().size();
        Assertions.assertEquals(initialSize,newSize);
    }

    public List<ModelOfBookTaking> getBookTakingLog (){
        List<ModelOfBookTaking> log = new ArrayList<>();
        try{
            Reader reader = new BufferedReader(new FileReader("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\bookTakingLog.json"));
            log = Arrays.asList(new Gson().fromJson(reader, ModelOfBookTaking[].class));
            reader.close();
        } catch (Exception e){
            System.out.println("Error while reading");
        }
        return log;
    }

}
