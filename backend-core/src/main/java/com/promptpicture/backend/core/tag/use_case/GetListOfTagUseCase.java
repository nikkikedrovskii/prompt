package com.promptpicture.backend.core.tag.use_case;

import com.promptpicture.backend.core.tag.adapter.TagRepositoryAdapter;
import com.promptpicture.backend.core.tag.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetListOfTagUseCase {

    private final TagRepositoryAdapter tagRepositoryAdapter;

    public List<Tag> execute() {
        return tagRepositoryAdapter.getListOfTag();
    }
}
