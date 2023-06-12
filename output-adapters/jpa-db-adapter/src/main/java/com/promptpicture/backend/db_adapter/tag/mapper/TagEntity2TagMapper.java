package com.promptpicture.backend.db_adapter.tag.mapper;

import com.promptpicture.backend.core.tag.domain.Tag;
import com.promptpicture.backend.jpa.tag.entity.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagEntity2TagMapper {

    @Mapping(target = "tagName", source = "tagName")
    Tag toTag(TagEntity from);

    List<Tag> toListOfTag(List<TagEntity> from);
}
