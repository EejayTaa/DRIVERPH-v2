package com.example.SpringBootREST.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SpringBootREST.dto.StudentDto;

import com.example.SpringBootREST.vo.StudentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<StudentVo> getStudents();

    void createStudent(StudentDto studentDto);

    List<StudentVo> getStudentById(long id);

    void deleteStudent(long id);

    int updateStudent(long id, StudentDto studentDto);

    Page<StudentVo> searchStudents(Page<StudentVo> of, @Param("studentDto") StudentDto studentDto);
}
