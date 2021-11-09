package com.simas.vismajavadevelopertask.controller;

import com.simas.vismajavadevelopertask.model.ModelOfBookTaking;
import com.simas.vismajavadevelopertask.service.ModelOfBookTakingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/visma-task/bookTaking")
public class ModelOfBookTakingController {

    private ModelOfBookTakingService modelOfBookTakingService;

    public ModelOfBookTakingController(ModelOfBookTakingService modelOfBookTakingService) {
        this.modelOfBookTakingService = modelOfBookTakingService;
    }

    @PostMapping("/take")
    public ResponseEntity<List<ModelOfBookTaking>> takeBooks (@RequestBody ModelOfBookTaking... modelOfBookTakings){
        return new ResponseEntity<List<ModelOfBookTaking>>(modelOfBookTakingService.takeBooks(modelOfBookTakings), HttpStatus.OK);
    }

}
