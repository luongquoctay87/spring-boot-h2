package com.sample.dto.request;

import com.sample.model.Status;
import com.sample.util.ValueOfEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserUpdateRequest implements Serializable {
    @NotNull
    @Min(1)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    @ValueOfEnum(message = "status must be any of enum (NONE, ACTIVE,INACTIVE)", enumClass = Status.class)
    private String status;
}
