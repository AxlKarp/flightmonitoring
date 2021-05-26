package com.example.flightmonitoring;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.Aleksey.flightsmonitoring.client.service.PlacesClient;
import lombok.Data;


@Data
public class PlacesDto {

    @JsonProperty("PlaceId")
    private String placeId;

    @JsonProperty("PlaceName")
    private String placeName;

    @JsonProperty("CountryId")
    private String countryId;

    @JsonProperty("RegionId")
    private String regionId;

    @JsonProperty("CityId")
    private String cityId;

    @JsonProperty("CountryName")
    private String countryName;
}