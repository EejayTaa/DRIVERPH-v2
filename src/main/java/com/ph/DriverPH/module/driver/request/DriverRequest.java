package com.ph.DriverPH.module.driver.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class DriverRequest {

    @JsonIgnore
    private String driverId;

    @NotNull(message = "First name is a required field.")
    private String firstName;

    @NotNull(message = "Middle name is a required field.")
    private String middleName;

    @NotNull(message = "Last name is a required field.")
    private String lastName;

    @NotNull(message = "Company is a required field.")
    private String company;

    @JsonIgnore
    private LocalDateTime date;

}
