package com.promptpicture.backend.entrypoint.rest.controller;

import com.promptpicture.backend.entrypoint.rest.adapter.TagAdapter;
import com.promptpicture.backend.entrypoint.rest.model.output.TagOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController {

    private final TagAdapter tagAdapter;

    @GetMapping(value = {"/tag"})
    public ResponseEntity<List<TagOutput>> getListOfTag() {
        var response = tagAdapter.getListOfTag();
        return ResponseEntity.ok(response);
    }

}
