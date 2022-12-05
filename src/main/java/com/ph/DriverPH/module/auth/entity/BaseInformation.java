package com.ph.DriverPH.module.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Embeddable
public class BaseInformation {

    private String firstName;
    private String lastName;
    private String middleName;
    private String email;

}
