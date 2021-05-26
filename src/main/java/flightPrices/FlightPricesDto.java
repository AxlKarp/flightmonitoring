package flightPrices;
import com.example.flightmonitoring.CurrencyDto;
import com.example.flightmonitoring.PlacesDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class FlightPricesDto {

    @JsonProperty("Quotas")
    private List<QuoteDto> quotas;

    @JsonProperty("Places")
    private List<PlacesDto> places;

    @JsonProperty("Carriers")
    private List<CarrierDto> carriers;

    @JsonProperty("Currencies")
    private List<CurrencyDto> currencies;
}
