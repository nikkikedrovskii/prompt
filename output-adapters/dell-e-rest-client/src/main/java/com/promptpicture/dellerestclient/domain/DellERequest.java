package com.promptpicture.dellerestclient.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DellERequest {

    String prompt;
    Integer n;
    String size;
    String response_format;

}
