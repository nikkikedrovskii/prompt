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
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PromptJpaRepositoryAdapter implements PromptRepositoryAdapter {

    private final PromptEntityRepository promptEntityRepository;
    private final PromptEntity2PromptMapper promptEntity2PromptMapper;

    @Override
    @Transactional
    public void savePromptPicture(Long promptId) {
        var promptEntity = promptEntityRepository.findById(promptId).get();
        if (!promptEntity.isSaved()) {
            promptEntity.setSaved(true);
            promptEntityRepository.save(promptEntity);
        }
    }

    @Override
    public Prompt savePromptPictureTemporarily(String promptText, String b64Json, UUID userId) {
        var promptEntity = createPromptEntity(b64Json,promptText, false);
        promptEntity.setUserId(userId);
        var savedPromptEntity = promptEntityRepository.save(promptEntity);
        return promptEntity2PromptMapper.toPrompt(savedPromptEntity);
    }

    @Override
    public List<Prompt> getListOfPrompt() {
        var listOfPromptEntity = promptEntityRepository.getPromptEntityBySavedIsTrue();
        return promptEntity2PromptMapper.toListOfPrompt(listOfPromptEntity);
    }

    @Override
    public Prompt findPromptById(Long id) {
        var promptEntity = promptEntityRepository.findById(id).get();
        return promptEntity2PromptMapper.toPrompt(promptEntity);
    }

    @Override
    public List<Prompt> getListOfPromptByUserId(UUID userId) {
        var listOfPromptEntity = promptEntityRepository.getPromptEntityByUserIdAndSavedIsFalseOrderByCreatedAtAsc(userId);
        return promptEntity2PromptMapper.toListOfPrompt(listOfPromptEntity);
    }

    @Override
    public void deleteByPromptId(Long promptId) {
         promptEntityRepository.deleteById(promptId);
    }

    private PromptEntity createPromptEntity(String b64TextJson, String promptText, boolean saved){
        var promptEntity = new PromptEntity();
        var promptPictureEntity = new PromptPictureEntity();
        promptPictureEntity.setText(promptText);
        promptPictureEntity.setFileB64JsonText(b64TextJson);
        promptEntity.setPromptPictureEntity(promptPictureEntity);
        promptEntity.setSaved(saved);
        return promptEntity;
    }
}
