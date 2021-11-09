package com.simas.vismajavadevelopertask.service;

import com.simas.vismajavadevelopertask.model.ModelOfBookTaking;

import java.util.List;

public interface ModelOfBookTakingService {

    List<ModelOfBookTaking> takeBooks (ModelOfBookTaking... modelOfBookTakings);

}
