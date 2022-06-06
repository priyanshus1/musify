package com.shukla.musify.service.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gender {
    @JsonProperty("Male") MALE,
    @JsonProperty("Female") FEMALE;
}
