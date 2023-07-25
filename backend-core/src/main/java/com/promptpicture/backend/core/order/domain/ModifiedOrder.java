package com.promptpicture.backend.core.order.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ModifiedOrder {

    private List<String> mapOfModifiedOrder;
}
