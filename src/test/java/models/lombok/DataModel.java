package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import models.Pet;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataModel {
    @JsonProperty("data")
    private Pet pet;

}
