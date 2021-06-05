package com.example.flightmonitoring;
import static com.example.flightmonitoring.UniRestServiceImpl.COUNTRIES_FORMAT;
import static com.example.flightmonitoring.UniRestServiceImpl.COUNTRIES_KEY;
import static com.example.flightmonitoring.UniRestServiceImpl.CURRENCIES_KEY;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.List;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class LocalisationClientImpl implements LocalisationClient {

    @Autowired
    private UniRestService uniRestService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<CountryDto> retrieveCountries(String locale) throws IOException {
        HttpResponse<JsonNode> response = uniRestService.get(String.format(COUNTRIES_FORMAT, locale));
        if (response.getStatus() != HttpStatus.SC_OK) {
            return null;
        }
        String jsonList = response.getBody().getObject().get(COUNTRIES_KEY).toString();
        return objectMapper.readValue(jsonList, new TypeReference<List<CountryDto>>() {
        });
    }

    @Override
    public List<CurrencyDto> retrieveCurrencies() throws IOException, UnirestException {
        HttpResponse<JsonNode> response = uniRestService.get(UniRestServiceImpl.CURRENCIES_FORMAT);
        if (response.getStatus() != HttpStatus.SC_OK) {
            return null;
        }
        String jsonList = response.getBody().getObject().get(CURRENCIES_KEY).toString();

        return objectMapper.readValue(jsonList, new TypeReference<List<CurrencyDto>>() {
        });
    }
}
