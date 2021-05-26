package flightPrices;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class QuoteDto {

    @JsonProperty("QuoteId")
    private Integer quoteId;

    @JsonProperty("MinPrice")
    private Integer minPrice;

    @JsonProperty("Direct")
    private boolean direct;

    @JsonProperty("OutboundLeg")
    private LegDto outBoundLeg;

    @JsonProperty("inboundLeg")
    private LegDto inBoundLeg;

    @JsonProperty("QuoteDateTime")
    private String quoteDateTime;
}
