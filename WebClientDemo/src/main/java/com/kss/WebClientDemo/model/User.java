package com.kss.WebClientDemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

    private int userId;
    private int id;
    private String title;
    private boolean completed;
}
