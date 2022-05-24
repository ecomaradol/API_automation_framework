package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;



@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    private Integer id;
    private String name;
    private String status;

}
