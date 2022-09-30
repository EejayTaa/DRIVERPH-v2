package com.ph.DriverPH.module.auth.service;


import com.ph.DriverPH.module.auth.entity.AuthUser;
import com.ph.DriverPH.module.auth.request.AuthUserRequest;

import java.util.Optional;

public interface AuthUserService {

//    List<StudentVo> getStudents();

    void register (AuthUserRequest authUserRequest);

    Optional<AuthUser> getUser(String username);



//    List<StudentVo> getStudentById(long id);
//
//    String deleteStudent(long id);
//
//    String updateStudent(long id, AuthUserRequest authUserRequest);
//
//    Page<StudentVo> searchStudents(Page<StudentVo> of, AuthUserRequest authUserRequest);


}
