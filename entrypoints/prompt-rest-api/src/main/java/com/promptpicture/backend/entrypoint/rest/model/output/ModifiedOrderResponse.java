package com.promptpicture.backend.entrypoint.rest.model.output;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ModifiedOrderResponse {

    List<String> modifiedOrderItem;
}
