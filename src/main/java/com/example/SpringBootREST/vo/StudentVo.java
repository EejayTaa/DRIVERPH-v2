package com.example.SpringBootREST.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data

public class StudentVo {

    private long id;
    private String firstName;
    private String lastName;


}
