package com.sample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum Status {
    @JsonProperty("none")
    NONE("none"),

    @JsonProperty("active")
    ACTIVE("active"),

    @JsonProperty("inactive")
    INACTIVE("inactive");

    Status(String status) {
    }
}
