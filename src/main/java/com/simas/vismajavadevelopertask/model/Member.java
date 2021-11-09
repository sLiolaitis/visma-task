package com.simas.vismajavadevelopertask.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class Member {

    private long memberId;
    private String name;
    private String surname;

}
