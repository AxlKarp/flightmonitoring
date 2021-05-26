package flightPrices;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Data
public class LegDto {

    @JsonProperty("OriginId")
    private Integer originId;

    @JsonProperty("DestinationId")
    private Integer destinationId;

    @JsonProperty("DepartureDate")
    private String departureDate;

    @JsonProperty("CarrierIds")
    private List<Integer> carrierIds;

}
