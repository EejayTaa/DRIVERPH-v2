package com.example.SpringBootREST.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SpringBootREST.dto.StudentDto;
import com.example.SpringBootREST.exception.ServiceException;
import com.example.SpringBootREST.service.StudentService;
import com.example.SpringBootREST.vo.StudentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Student API.
 *
 * @author Eejay Taa
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("/createStudent")
    public String createStudent(@Valid @RequestBody StudentDto studentDto){
        logger.info("createStudent" + " " + studentDto);
        //Check if required fields are satisfied.
        if(!StringUtils.isNotEmpty(studentDto.getFirstName())){
            throw new ServiceException("First name is a required field.", 404);
        }

        return studentService.createStudent(studentDto);
    }


    @GetMapping("/getStudents")
    public List<StudentVo> getStudents(){
//        List<Student> students = studentServiceImpl.getStudents();
//        List<StudentDto> studentResponseList = new ArrayList<>();
//        students.stream().forEach(student -> studentResponseList.add(new StudentDto(student)));
        return studentService.getStudents();
    }

    @PostMapping("/searchStudents")
    public Page<StudentVo> searchStudents(@Validated @RequestBody StudentDto studentDto){
        Page<StudentVo> of = Page.of(studentDto.getPage(), studentDto.getPageSize());
        Page<StudentVo> page = studentService.searchStudents(of, studentDto);
        return page;
    }

    @GetMapping("/getStudentById/{id}")
    public List<StudentVo> getStudentById(@PathVariable long id){
        return studentService.getStudentById(id);
    }


    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable long id){
        return studentService.deleteStudent(id);
    }

    @PutMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable long id, @RequestBody StudentDto studentDto){
        logger.info("updateStudent" + studentDto);
        return studentService.updateStudent(id, studentDto);
    }




    //Not recommended
//    @GetMapping("/")
//    public List<Student> getStudent(){
//        return studentService.getAllStudents();
//    }
//    @GetMapping("/")
//    public List<StudentResponse> getStudent(){
//
//
//        List<Student> studentList = studentServiceImpl.getAllStudents();
//        List<StudentResponse> studentResponseList = new ArrayList<>();
//        studentList.stream().forEach(student -> {
//            studentResponseList.add(new StudentResponse(student));
//        });
//
//
//        return studentResponseList;
//    }
//
//    @GetMapping("/like/{firstName}")
//    public List<StudentResponse> getStudentLike(@PathVariable String firstName){
//        List<Student> students = studentServiceImpl.findStudentLike(firstName);
//        List<StudentResponse> studentResponseList = new ArrayList<>();
//        students.stream().forEach(student -> {
//            studentResponseList.add(new StudentResponse(student));
//        });
//
//        return studentResponseList;
//    }
//
//    @GetMapping("/getStudentsSorted")
//    public List<StudentResponse> getStudentSorted(){
//        List<Student> students = studentServiceImpl.getStudentSorted();
//        List<StudentResponse> studentResponseList = new ArrayList<>();
//        students.stream().forEach(student -> {
//            studentResponseList.add(new StudentResponse(student));
//        });
//
//        return studentResponseList;
//    }
//    @GetMapping("/find/{firstName}")
//    public List<StudentResponse> getStudentByFirstName(@PathVariable String firstName){
//        List<Student> studentList = studentServiceImpl.getStudentByFirstName(firstName);
//        List<StudentResponse> studentResponseList = new ArrayList<>();
//        studentList.stream().forEach(student -> {
//            studentResponseList.add(new StudentResponse(student));
//        });
//        return studentResponseList;
//    }
//
//    @GetMapping("/findByFirstNameAndLastName/{firstName}/{lastName}")
//    public StudentResponse getStudentByFirstNameAndLastName(@PathVariable  String firstName, @PathVariable String lastName){
//        return new StudentResponse(studentServiceImpl.getStudentByFirstNameAndLastName(firstName, lastName));
//    }
//
//    @GetMapping("/findByFirstNameOrLastName/{firstName}/{lastName}")
//    public List<StudentResponse> getStudentByFirstNameOrLastName(@PathVariable String firstName, @PathVariable String lastName){
//        List<Student> studentList = studentServiceImpl.getStudentByFirstNameOrLastName(firstName, lastName);
//        List<StudentResponse> studentResponseList = new ArrayList<>();
//        studentList.stream().forEach(student -> {
//            studentResponseList.add(new StudentResponse(student));
//        });
//        return studentResponseList;
//    }
//
//    @GetMapping("/findStudentByFirstNameIn")
//    public List<StudentResponse> getStudentByFirstNameIn(@RequestBody InQueryRequest inQueryRequest){
//        List<Student> students =  studentServiceImpl.getStudentByFirstNameIn(inQueryRequest);
//        List<StudentResponse> studentResponseList = new ArrayList<>();
//
//        students.stream().forEach(student -> {
//            studentResponseList.add(new StudentResponse(student));
//        });
//        return studentResponseList;
//    }
//
//    @GetMapping("/findById/{id}")
//    public List<StudentResponse> getStudentById(@PathVariable long id){
//        List<Student> studentList = studentServiceImpl.getStudentById(id);
//        List<StudentResponse> studentResponseList = new ArrayList<>();
//        studentList.stream().forEach(student -> {
//            studentResponseList.add(new StudentResponse(student));
//        });
//        return studentResponseList;
//    }
//
//    @GetMapping("/findStudentsWithPagination")
//    public List<StudentResponse> getStudentsWithPagination(@RequestParam int pageNo, @RequestParam int pageSize){
//        List<Student> students = studentServiceImpl.getStudentWithPagination(pageNo, pageSize);
//        List<StudentResponse> studentResponseList = new ArrayList<>();
//
//        students.stream().forEach(student -> {
//            studentResponseList.add(new StudentResponse(student));
//        });
//        return studentResponseList;
//    }
//
//    @PostMapping("/")
//    public StudentResponse createStudent(@Valid @RequestBody StudentRequest studentRequest){
//        logger.info("studentRequest = " + studentRequest);
//        Student student = studentServiceImpl.createStudent(studentRequest);
//        return new StudentResponse(student);
//    }
//
//    @PutMapping("/")
//    public StudentResponse updateStudent(@Valid @RequestBody UpdateRequest updateRequest){
//     Student student = studentServiceImpl.updateStudent(updateRequest);
//     return new StudentResponse(student);
//    }
//
//    //Query Params
////    @DeleteMapping("/delete")
////    public String deleteStudent(@RequestParam long id){
////        return studentService.deleteStudent(id);
////    }
//
//    //Path variable
//    @DeleteMapping("/delete/{id}")
//    public String deleteStudent(@PathVariable long id){
//        return studentServiceImpl.deleteStudent(id);
//    }



}
