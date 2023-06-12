package com.promptpicture.backend.core.tag.adapter;

import com.promptpicture.backend.core.tag.domain.Tag;

import java.util.List;

public interface TagRepositoryAdapter {

    void saveUserTag(List<String> listOfUserTags);

    List<Tag> getListOfTag();

}
