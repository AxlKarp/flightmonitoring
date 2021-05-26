package com.example.flightmonitoring;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

    @Data
    public class CountryDto {

        @JsonProperty("Code")
        private String code;

        @JsonProperty("Name")
        private String name;

    }