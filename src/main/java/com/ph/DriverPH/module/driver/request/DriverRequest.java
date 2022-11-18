package com.ph.DriverPH.module.driver.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author Administrator
 */
@Getter
@Setter
public class DriverRequest {

    @JsonIgnore
    private String driverId;

    @NotBlank(message = "First name is a required field.")
    private String firstName;

    @NotBlank(message = "Middle name is a required field.")
    private String middleName;

    @NotBlank(message = "Last name is a required field.")
    private String lastName;

    @NotBlank(message = "Company is a required field.")
    private String company;

    @JsonIgnore
    private LocalDateTime date;

}
