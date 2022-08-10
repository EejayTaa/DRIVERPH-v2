package com.example.SpringBootREST.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SpringBootREST.dto.StudentDto;
import com.example.SpringBootREST.vo.StudentVo;

import java.util.List;

public interface StudentService  {

    List<StudentVo> getStudents();

    String createStudent(StudentDto studentDto);

    List<StudentVo> getStudentById(long id);

    String deleteStudent(long id);

    String updateStudent(long id, StudentDto studentDto);

    Page<StudentVo> searchStudents(Page<StudentVo> of, StudentDto studentDto);
}
