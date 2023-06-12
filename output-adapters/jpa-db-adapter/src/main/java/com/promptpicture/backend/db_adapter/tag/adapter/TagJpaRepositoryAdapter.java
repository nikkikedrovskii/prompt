package com.promptpicture.backend.db_adapter.tag.adapter;

import com.promptpicture.backend.core.tag.adapter.TagRepositoryAdapter;
import com.promptpicture.backend.core.tag.domain.Tag;
import com.promptpicture.backend.db_adapter.tag.mapper.TagEntity2TagMapper;
import com.promptpicture.backend.jpa.tag.entity.TagEntity;
import com.promptpicture.backend.jpa.tag.repository.TagEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TagJpaRepositoryAdapter implements TagRepositoryAdapter {

    private final TagEntityRepository tagEntityRepository;
    private final TagEntity2TagMapper tagEntity2TagMapper;

    @Override
    public void saveUserTag(List<String> listOfUserTags) {

            var tagEntityList = tagEntityRepository.findAllByTagNameIn(listOfUserTags);
            var dbTagsNames = tagEntityList.stream().map(TagEntity::getTagName).toList();

            listOfUserTags.forEach(tagOfUser -> {
                if (!dbTagsNames.contains(tagOfUser)){
                    var newTag = new TagEntity();
                    newTag.setTagName(tagOfUser);
                    tagEntityRepository.save(newTag);
                }
            });
    }

    @Override
    public List<Tag> getListOfTag() {
        var listOfTagsEntity = tagEntityRepository.findAll();
        return tagEntity2TagMapper.toListOfTag(listOfTagsEntity);
    }
}
