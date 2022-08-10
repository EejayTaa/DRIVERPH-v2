package com.example.SpringBootREST.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class StudentDto implements Serializable {

    private long id;
    private String firstName;
    private String lastName;
    private Integer page;
    private Integer pageSize;


}
