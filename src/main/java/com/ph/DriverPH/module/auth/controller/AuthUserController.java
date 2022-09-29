package com.ph.DriverPH.module.auth.controller;

import com.ph.DriverPH.common.Result;
import com.ph.DriverPH.common.ResultGenerator;
import com.ph.DriverPH.module.auth.request.AuthUserRequest;
import com.ph.DriverPH.module.auth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    AuthUserService authUserService;

    @PostMapping("/register")
    public Result register(@RequestBody @Validated AuthUserRequest authUserRequest){
        authUserService.register(authUserRequest);
        return ResultGenerator.genCreatedResult();
    }




//
//    @PostMapping("/searchStudents")
//    public Page<StudentVo> searchStudents(@Validated @RequestBody AuthUserRequest studentDto){
//        Page<StudentVo> of = Page.of(studentDto.getPage(), studentDto.getPageSize());
//        Page<StudentVo> page = authUserService.searchStudents(of, studentDto);
//        return page;
//    }
//
//    @GetMapping("/getStudentById/{id}")
//    public List<StudentVo> getStudentById(@PathVariable long id){
//        return authUserService.getStudentById(id);
//    }
//
//
//    @DeleteMapping("/deleteStudent/{id}")
//    public String deleteStudent(@PathVariable long id){
//        return authUserService.deleteStudent(id);
//    }
//
//    @PutMapping("/updateStudent/{id}")
//    public String updateStudent(@PathVariable long id, @RequestBody AuthUserRequest studentDto){
//        logger.info("updateStudent" + studentDto);
//        return authUserService.updateStudent(id, studentDto);
//    }




    //Not recommended
//    @GetMapping("/")
//    public List<AuthUser> getStudent(){
//        return authUserService.getAllStudents();
//    }
//    @GetMapping("/")
//    public List<StudentResponse> getStudent(){
//
//
//        List<AuthUser> studentList = studentServiceImpl.getAllStudents();
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
//        List<AuthUser> students = studentServiceImpl.findStudentLike(firstName);
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
//        List<AuthUser> students = studentServiceImpl.getStudentSorted();
//        List<StudentResponse> studentResponseList = new ArrayList<>();
//        students.stream().forEach(student -> {
//            studentResponseList.add(new StudentResponse(student));
//        });
//
//        return studentResponseList;
//    }
//    @GetMapping("/find/{firstName}")
//    public List<StudentResponse> getStudentByFirstName(@PathVariable String firstName){
//        List<AuthUser> studentList = studentServiceImpl.getStudentByFirstName(firstName);
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
//        List<AuthUser> studentList = studentServiceImpl.getStudentByFirstNameOrLastName(firstName, lastName);
//        List<StudentResponse> studentResponseList = new ArrayList<>();
//        studentList.stream().forEach(student -> {
//            studentResponseList.add(new StudentResponse(student));
//        });
//        return studentResponseList;
//    }
//
//    @GetMapping("/findStudentByFirstNameIn")
//    public List<StudentResponse> getStudentByFirstNameIn(@RequestBody InQueryRequest inQueryRequest){
//        List<AuthUser> students =  studentServiceImpl.getStudentByFirstNameIn(inQueryRequest);
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
//        List<AuthUser> studentList = studentServiceImpl.getStudentById(id);
//        List<StudentResponse> studentResponseList = new ArrayList<>();
//        studentList.stream().forEach(student -> {
//            studentResponseList.add(new StudentResponse(student));
//        });
//        return studentResponseList;
//    }
//
//    @GetMapping("/findStudentsWithPagination")
//    public List<StudentResponse> getStudentsWithPagination(@RequestParam int pageNo, @RequestParam int pageSize){
//        List<AuthUser> students = studentServiceImpl.getStudentWithPagination(pageNo, pageSize);
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
//        AuthUser student = studentServiceImpl.createStudent(studentRequest);
//        return new StudentResponse(student);
//    }
//
//    @PutMapping("/")
//    public StudentResponse updateStudent(@Valid @RequestBody UpdateRequest updateRequest){
//     AuthUser student = studentServiceImpl.updateStudent(updateRequest);
//     return new StudentResponse(student);
//    }
//
//    //Query Params
////    @DeleteMapping("/delete")
////    public String deleteStudent(@RequestParam long id){
////        return authUserService.deleteStudent(id);
////    }
//
//    //Path variable
//    @DeleteMapping("/delete/{id}")
//    public String deleteStudent(@PathVariable long id){
//        return studentServiceImpl.deleteStudent(id);
//    }



}
