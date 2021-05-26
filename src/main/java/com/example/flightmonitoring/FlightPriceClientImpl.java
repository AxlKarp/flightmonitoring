package com.example.flightmonitoring;
import static com.example.flightmonitoring.UniRestServiceImpl.PLACES_KEY;
import static com.github.Aleksey.flightsmonitoring.client.service.impl.UniRestServiceImpl.CURRENCIES_KEY;
import static com.github.Aleksey.flightsmonitoring.client.service.impl.UniRestServiceImpl.PLACES_KEY;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.Aleksey.flightsmonitoring.client.dto.CarrierDto;
import com.github.Aleksey.flightsmonitoring.client.dto.CurrencyDto;
import com.github.Aleksey.flightsmonitoring.client.dto.FlightPricesDto;
import com.github.Aleksey.flightsmonitoring.client.dto.PlaceDto;
import com.github.Aleksey.flightsmonitoring.client.dto.QuoteDto;
import com.github.Aleksey.flightsmonitoring.client.dto.ValidationErrorDto;
import com.github.Aleksey.flightsmonitoring.client.service.FlightPricesClient;
import com.github.Aleksey.flightsmonitoring.client.service.UniRestService;
import com.github.Aleksey.flightsmonitoring.exception.FlightClientException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import java.io.IOException;
import java.util.List;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightPriceClientImpl implements FlightPrices {

    public static final String BROWSE_QUOTES_FORMAT = "/apiservices/browsequotes/v1.0/%s/%s/%s/%s/%s/%s";
    public static final String OPTIONAL_BROWSE_QUOTES_FORMAT = BROWSE_QUOTES_FORMAT + "?inboundpartialdate = %s";

    public static final String QUOTES_KEY = "Quotes";
    public static final String ROUTES_KEY = "Routes";
    public static final String DATES_KEY = "Dates";
    public static final String CARRIERS_KEY = "Carriers";
    public static final String VALIDATIONS_KEY = "ValidationsErrors";

    @Autowired
    private UniRestService uniRestService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public FlightPricesDto browseQuotes(String country, String currency, String locale, String originPlace,
                                        String destinationPlace, String outBoundPartialDate) {
        HttpResponse<JsonNode> response = uniRestService.get(String.format(BROWSE_QUOTES_FORMAT, country, currency,
                locale, originPlace));
        return mapToObject(response);
    }

    private FlightPricesDto mapToObject(HttpResponse<JsonNode> response) {
        if (response.getStatus() == HttpStatus.SC_OK) {
            FlightPricesDto flightPricesDto = new FligthPricesDto();
            flightPricesDto.setQuotas(readValue(response.getBody().getObject().get(QUOTES_KEY).toString(),
                    new TypeReference<List<QuoteDto>>() {
                    }));
            flightPricesDto.setCarriers(readValue(response.getBody().getObject().get(CARRIERS_KEY).toString(),
                    new TypeReference<List<CarrierDto>>() {
                    }));
            flightPricesDto.setCurrencies(readValue(response.getBody().getObject().get(CARRIERS_KEY).toString(),
                    new TypeReference<List<CurrencyDto>>() {
                    }));
            flightPricesDto.setPlaces(readValue(response.getBody().getObject().get(PLACES_KEY).toString(),
                    new TypeReference<List<PlacesDto>>() {
                    }));
            return FlightPricesDto;
        }
        throw new FlightClientException(String.format("There are validation errors. StatusCode is %s", response.getStatus()),
                readValue(response.getBody().getObject().get(VALIDATIONS_KEY).toString(),
                        new TypeReference<List<ValidationErrorDto>>() {
                        }));
    }
    private <T> List<T> readValue(String resultAsString, TypeReference<List<T>> valueTypeRef){
        List<T> list;
        try{
            list = objectMapper.readValue(resultAsString,valueTypeRef);
        }catch (IOException e){
            throw new FlightClientException("Object Mapping failure.", e);
        }
        return list;
    }
}
