package com.example.flightmonitoring;
import static com.example.flightmonitoring.UniRestServiceImpl.PLACES_FORMAT;
import static com.example.flightmonitoring.UniRestServiceImpl.PLACES_KEY;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.List;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlacesClientImpl implements PlacesClient {

    @Autowired
    private UniRestService uniRestService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<PlacesDto> retrieveListPlaces(String query, String country, String currency, String locale)
            throws IOException, UnirestException {
        HttpResponse<JsonNode> response = uniRestService
                .get(String.format(PLACES_FORMAT, country, currency, locale, query));

        if (response.getStatus() != HttpStatus.SC_OK) {
            return null;
        }

        String jsonList = response.getBody().getObject().get(PLACES_KEY).toString();

        return objectMapper.readValue(jsonList, new TypeReference<List<PlacesDto>>() {
        });
    }
}
