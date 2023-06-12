package com.promptpicture.backend.entrypoint.rest.mapper;

import com.promptpicture.backend.core.tag.domain.Tag;
import com.promptpicture.backend.entrypoint.rest.model.output.TagOutput;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Tag2TagOutputMapper {

    TagOutput toPromptOutput(Tag from);

    List<TagOutput> toListOfPromptOutput(List<Tag> from);
}
