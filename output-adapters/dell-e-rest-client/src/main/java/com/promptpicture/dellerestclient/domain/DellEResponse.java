package com.promptpicture.dellerestclient.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DellEResponse {

    @JsonProperty("created")
    String created;

    @JsonProperty("data")
    List<DataUrl> data;

}
