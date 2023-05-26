package com.promptpicture.backend.db_adapter.prompt.adapter;

import com.promptpicture.backend.core.prompt.adapter.PromptRepositoryAdapter;
import com.promptpicture.backend.core.prompt.domain.Prompt;
import com.promptpicture.backend.db_adapter.prompt.mapper.PromptEntity2PromptMapper;
import com.promptpicture.backend.jpa.prompt.entity.PromptEntity;
import com.promptpicture.backend.jpa.prompt.entity.PromptPictureEntity;
import com.promptpicture.backend.jpa.prompt.repository.PromptEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PromptJpaRepositoryAdapter implements PromptRepositoryAdapter {

    private final PromptEntityRepository promptEntityRepository;
    private final PromptEntity2PromptMapper promptEntity2PromptMapper;

    @Override
    @Transactional
    public void savePromptPicture(String promptText, String b64Json) {
        var promptEntity = createPromptEntity(b64Json,promptText);
        promptEntityRepository.save(promptEntity);
    }

    @Override
    public List<Prompt> getListOfPrompt() {
        var listOfPromptEntity = promptEntityRepository.findAll();
        return promptEntity2PromptMapper.toListOfPrompt(listOfPromptEntity);
    }

    @Override
    public Prompt findPromptById(Long id) {
        var promptEntity = promptEntityRepository.findById(id).get();
        return promptEntity2PromptMapper.toPrompt(promptEntity);
    }

    private PromptEntity createPromptEntity(String b64TextJson, String promptText){
        var promptEntity = new PromptEntity();
        var promptPictureEntity = new PromptPictureEntity();
        promptPictureEntity.setText(promptText);
        promptPictureEntity.setFileB64JsonText(b64TextJson);
        promptEntity.setPromptPictureEntity(promptPictureEntity);
        return promptEntity;
    }
}
