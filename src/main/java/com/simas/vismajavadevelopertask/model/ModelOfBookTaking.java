package com.simas.vismajavadevelopertask.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModelOfBookTaking {

    private long memberId;
    private String bookGuid;
    private long days;

}
