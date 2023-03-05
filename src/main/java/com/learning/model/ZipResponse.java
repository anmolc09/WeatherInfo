package com.learning.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZipResponse {

    @JsonProperty("zip")
    private Long pincode;
    private String name;
    private Double lon;
    private Double lat;
    private String country;
}
