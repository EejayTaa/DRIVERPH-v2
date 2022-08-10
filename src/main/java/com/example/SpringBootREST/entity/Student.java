package com.example.SpringBootREST.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;


@Data
@TableName("student")
public class Student {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
//    private String phoneNumber;
//    private String address;
//    private String studentNumber;
//    private String gender;


}
