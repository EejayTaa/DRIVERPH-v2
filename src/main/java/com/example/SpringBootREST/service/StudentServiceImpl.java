package com.example.SpringBootREST.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SpringBootREST.controller.StudentController;
import com.example.SpringBootREST.dto.StudentDto;

import com.example.SpringBootREST.entity.Student;
import com.example.SpringBootREST.exception.ErrorMessages;
import com.example.SpringBootREST.mapper.StudentMapper;
import com.example.SpringBootREST.vo.StudentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentMapper studentMapper;
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public List<StudentVo> getStudents() {
        return studentMapper.getStudents();
    }

    @Override
    public String createStudent(StudentDto studentDto) {

        studentMapper.createStudent(studentDto);
        return "Student is successfully created.";
    }

    @Override
    public List<StudentVo> getStudentById(long id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public String deleteStudent(long id) {
        studentMapper.deleteStudent(id);
        return "Student is successfully deleted.";
    }

    @Override
    public String updateStudent(long id, StudentDto studentDto) {
        Integer updateStudent = studentMapper.updateStudent(id, studentDto);

        if(updateStudent != 1){
            return "Student is not successfully updated.";
        }
        logger.info("studentMapper response " + studentMapper.updateStudent(id, studentDto));
        return "Student is successfully updated." ;
    }

    @Override
    public Page<StudentVo> searchStudents(Page<StudentVo> of, StudentDto studentDto) {
        return studentMapper.searchStudents(of, studentDto);

    }
}
