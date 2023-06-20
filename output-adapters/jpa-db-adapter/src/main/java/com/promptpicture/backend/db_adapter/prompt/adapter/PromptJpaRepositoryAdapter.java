package com.promptpicture.backend.db_adapter.prompt.adapter;

import com.promptpicture.backend.core.prompt.adapter.PromptRepositoryAdapter;
import com.promptpicture.backend.core.prompt.domain.Prompt;
import com.promptpicture.backend.core.prompt.domain.PromptFilter;
import com.promptpicture.backend.db_adapter.prompt.mapper.PromptEntity2PromptMapper;
import com.promptpicture.backend.jpa.customer.entity.CustomerEntity;
import com.promptpicture.backend.jpa.customer.repository.CustomerEntityRepository;
import com.promptpicture.backend.jpa.prompt.entity.PromptEntity;
import com.promptpicture.backend.jpa.prompt.entity.PromptPictureEntity;
import com.promptpicture.backend.jpa.prompt.repository.PromptEntityRepository;
import com.promptpicture.backend.jpa.tag.entity.TagEntity;
import com.promptpicture.backend.jpa.tag.repository.TagEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PromptJpaRepositoryAdapter implements PromptRepositoryAdapter {

    private final PromptEntityRepository promptEntityRepository;
    private final CustomerEntityRepository customerEntityRepository;
    private final PromptEntity2PromptMapper promptEntity2PromptMapper;
    private final TagEntityRepository tagEntityRepository;

    @Override
    @Transactional
    public void savePromptPicture(Long promptId, List<String> listOfTags) {
        var promptEntity = promptEntityRepository.findById(promptId).get();

        if (!promptEntity.isSaved()) {
            if (!listOfTags.isEmpty()) {
                var tagEntity = getTags(listOfTags);
                promptEntity.setPromptTags(tagEntity);
            }
            promptEntity.setSaved(true);
            promptEntityRepository.save(promptEntity);
        }
    }
    private List<TagEntity> getTags(List<String> userListTag){
        return tagEntityRepository.findAllByTagNameIn(userListTag);
    }

    @Override
    public Prompt savePromptPictureTemporarily(String promptText, String b64Json, UUID userId) {
        var promptEntity = createPromptEntity(b64Json,promptText, false);
        var customerEntity = customerEntityRepository.findByExternalCustomerId(userId);
        if (customerEntity.isPresent()) {
            promptEntity.setCustomerEntity(customerEntity.get());
        } else {
            var newCustomerEntity = new CustomerEntity();
            newCustomerEntity.setExternalCustomerId(userId);
            promptEntity.setCustomerEntity(newCustomerEntity);
        }
        var savedPromptEntity = promptEntityRepository.save(promptEntity);
        return promptEntity2PromptMapper.toPrompt(savedPromptEntity);
    }

    @Override
    public List<Prompt> getListOfPrompt(PromptFilter promptFilter) {

        var listOfTag = promptFilter.getListOfTag();
        var promptEntities = promptEntityRepository.getPromptEntityBySavedIsTrue();

         if (!listOfTag.isEmpty()) {
             var filteredPromptEntities = promptEntities.stream().filter(promptEntity -> {
                         var promptEntityTag = promptEntity.getPromptTags()
                                 .stream()
                                 .map(TagEntity::getTagName)
                                 .toList();
                       return promptEntityTag.containsAll(listOfTag) && promptEntity.isSaved();
                     }
             ).toList();

             return promptEntity2PromptMapper.toListOfPrompt(filteredPromptEntities);
         }
          return  promptEntity2PromptMapper.toListOfPrompt(promptEntities);

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
        promptEntity.setDescription("Description of prompt");
        promptEntity.setPrice(BigDecimal.valueOf(15.0));

        return promptEntity;
    }
}
