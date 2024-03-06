package com.sample.dto.request;

import com.sample.model.Status;
import com.sample.util.ValueOfEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserCreationRequest implements Serializable {
    @NotBlank(message = "firstName must be not blank")
    private String firstName;

    @NotBlank(message = "lastName must be not blank")
    private String lastName;

    @NotBlank(message = "phone must be not blank")
    private String phone;

    @NotBlank(message = "email must be not blank")
    @Email(message = "email format is invalid")
    private String email;

    @ValueOfEnum(message = "status must be any of enum (active,inactive,none)", enumClass = Status.class)
    private String status;
}
