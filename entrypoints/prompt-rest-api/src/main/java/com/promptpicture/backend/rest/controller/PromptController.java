package com.promptpicture.backend.rest.controller;

import com.promptpicture.backend.rest.adapter.PromptAdapter;
import com.promptpicture.backend.rest.model.input.CreatePromptInput;
import com.promptpicture.backend.rest.model.input.GeneratePictureInput;
import com.promptpicture.backend.rest.model.output.GeneratePictureOutput;
import com.promptpicture.backend.rest.model.output.PromptOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PromptController {

    private final PromptAdapter promptAdapter;

    @GetMapping(
            value = {"/prompt/generate"},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<GeneratePictureOutput> generatePicture(@RequestBody GeneratePictureInput generatePictureInput) {
         var promptText = generatePictureInput.getPromptText();
         var response = promptAdapter.generatePicture(promptText);
         return ResponseEntity.ok(response);
    }

    @GetMapping(
            value = {"/prompt/picture"},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<List<PromptOutput>> getListOfPrompt() {
        var response = promptAdapter.getListOfPrompt();
        return ResponseEntity.ok(response);
    }

    @GetMapping(
            value = {"/prompt/picture/{promptId}"},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<PromptOutput> getPromptDetail(@PathVariable Long promptId) {
        var response = promptAdapter.getPromptDetail(promptId);
        return ResponseEntity.ok(response);
    }

    @PostMapping(
            value = {"/prompt/picture"},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<Void> createPicture(@RequestBody CreatePromptInput createPromptInput) {
        promptAdapter.createPrompt(createPromptInput);
        return ResponseEntity.ok().build();
    }
}
