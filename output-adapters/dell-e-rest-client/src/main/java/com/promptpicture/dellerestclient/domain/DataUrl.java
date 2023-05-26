package com.promptpicture.dellerestclient.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataUrl {

    @JsonProperty("b64_json")
    String b64Json;
}
