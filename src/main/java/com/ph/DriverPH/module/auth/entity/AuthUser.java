package com.ph.DriverPH.module.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.ph.DriverPH.common.entity.BaseEntity;
import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


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
    private String roles;
//    private BaseInformation baseInformation;

}
