package com.promptpicture.backend.entrypoint.rest.controller;

import com.promptpicture.backend.core.prompt.domain.PromptFilter;
import com.promptpicture.backend.entrypoint.rest.adapter.PromptAdapter;
import com.promptpicture.backend.entrypoint.rest.model.input.CreatePromptRequest;
import com.promptpicture.backend.entrypoint.rest.model.input.GeneratePictureRequest;
import com.promptpicture.backend.entrypoint.rest.model.output.GeneratePictureResponse;
import com.promptpicture.backend.entrypoint.rest.model.output.PromptResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PromptController {

    private final PromptAdapter promptAdapter;

    @PostMapping(value = {"/prompt/generate"})
    public ResponseEntity<GeneratePictureResponse> generatePicture(@RequestBody GeneratePictureRequest generatePictureRequest) {
         var promptText = generatePictureRequest.getPromptText();
         var userId = generatePictureRequest.getUserId();
         var response = promptAdapter.generatePicture(promptText, userId);
         return ResponseEntity.ok(response);
    }

    @GetMapping(value = {"/prompt/picture"})
    public ResponseEntity<List<PromptResponse>> getListOfPrompt(@RequestParam(value = "listOfTag", required = false) String listOfTag) {
        var promptFilter = new PromptFilter();
        if (!listOfTag.isBlank()) {
            promptFilter.setListOfTag(Arrays.stream(listOfTag.split(",")).toList());
        }
        var response = promptAdapter.getListOfPrompt(promptFilter);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = {"/prompt/picture/{promptId}"})
    public ResponseEntity<PromptResponse> getPromptDetail(@PathVariable Long promptId) {
        var response = promptAdapter.getPromptDetail(promptId);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = {"/prompt/{userId}/picture"})
    public ResponseEntity<List<PromptResponse>> getListOfPictureByUserId(@PathVariable UUID userId) {
        var response = promptAdapter.getListOfPromptByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = {"/prompt/picture"})
    public ResponseEntity<Void> createPicture(@RequestBody CreatePromptRequest createPromptRequest) {
        promptAdapter.createPrompt(createPromptRequest);
        return ResponseEntity.ok().build();
    }
}
