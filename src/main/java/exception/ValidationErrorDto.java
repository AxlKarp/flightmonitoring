package exception;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class ValidationErrorDto {
    @JsonProperty("ParameterName")
    private String parameterName;

    @JsonProperty("ParameterValue")
    private String parameterValue;

    @JsonProperty("Message")
    private String message;
}
