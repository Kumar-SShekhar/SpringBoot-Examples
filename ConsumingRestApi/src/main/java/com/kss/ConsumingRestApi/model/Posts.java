package com.kss.ConsumingRestApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//
//public class Posts {
//    private Integer id;
//    private String title;
//    private String body;
//    private int userId;
//    private ArrayList<java.lang.Object> tags;
//    private int reactions;
//
//}

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Posts {
    private Integer id;
    private String title;
    private String body;
    private int userId;
    private ArrayList<String> tags; // Change the type to ArrayList<String>
    private int reactions;
}
