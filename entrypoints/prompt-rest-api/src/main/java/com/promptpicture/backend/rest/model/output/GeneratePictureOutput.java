package com.promptpicture.backend.rest.model.output;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeneratePictureOutput {

    private String promptText;
    private String b64Json;

}
