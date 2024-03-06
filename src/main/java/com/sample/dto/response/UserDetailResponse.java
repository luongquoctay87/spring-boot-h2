package com.sample.dto.response;

import com.sample.model.Status;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserDetailResponse implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Status status;
}
