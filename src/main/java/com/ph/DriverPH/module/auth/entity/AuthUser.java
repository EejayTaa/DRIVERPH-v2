package com.ph.DriverPH.module.auth.entity;


import com.ph.DriverPH.common.entity.BaseEntity;
import lombok.*;
import javax.persistence.Entity;


/**
 * @author Eejay Taa
 */
@Getter
@Builder
@Entity(name="user")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class AuthUser extends BaseEntity {

    private String username;

    private String password;

    private String email;

}
