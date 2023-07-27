package com.promptpicture.backend.db_adapter.prompt.adapter;

import com.promptpicture.backend.core.exception.business.BadRequestException;
import com.promptpicture.backend.core.prompt.adapter.PromptRepositoryAdapter;
import com.promptpicture.backend.core.prompt.domain.IndividualPrompt;
import com.promptpicture.backend.core.prompt.domain.Prompt;
import com.promptpicture.backend.core.prompt.domain.PromptFilter;
import com.promptpicture.backend.db_adapter.prompt.mapper.PromptEntity2IndividualPromptMapper;
import com.promptpicture.backend.db_adapter.prompt.mapper.PromptEntity2PromptMapper;
import com.promptpicture.backend.jpa.customer.entity.CustomerEntity;
import com.promptpicture.backend.jpa.customer.repository.CustomerEntityRepository;
import com.promptpicture.backend.jpa.price.repository.PriceEntityRepository;
import com.promptpicture.backend.jpa.prompt.entity.PromptEntity;
import com.promptpicture.backend.jpa.prompt.entity.PromptPictureEntity;
import com.promptpicture.backend.jpa.prompt.repository.PromptEntityRepository;
import com.promptpicture.backend.jpa.tag.entity.TagEntity;
import com.promptpicture.backend.jpa.tag.repository.TagEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.promptpicture.backend.core.exception.error_code.ErrorCode.PROMPT_NOT_FOUND;
import static com.promptpicture.backend.core.exception.error_code.ErrorCode.USER_DOES_NOT_EXIST;

@Component
@RequiredArgsConstructor
public class PromptJpaRepositoryAdapter implements PromptRepositoryAdapter {

    private static final String CZECH_REPUBLIC_COUNTRY_CODE = "CZ";
    private final PromptEntityRepository promptEntityRepository;
    private final CustomerEntityRepository customerEntityRepository;
    private final PromptEntity2PromptMapper promptEntity2PromptMapper;
    private final TagEntityRepository tagEntityRepository;
    private final PromptEntity2IndividualPromptMapper promptEntity2IndividualPromptMapper;

    @Override
    @Transactional
    public void savePromptPicture(Long promptId, List<String> listOfTags) {
        var promptEntity = promptEntityRepository.findById(promptId)
                .orElseThrow(() -> new BadRequestException(USER_DOES_NOT_EXIST));

        if (!promptEntity.isSaved()) {
            if (!listOfTags.isEmpty()) {
                var tagEntity = getTags(listOfTags);
                promptEntity.setPromptTags(tagEntity);
            }
            promptEntity.setSaved(true);
            promptEntityRepository.save(promptEntity);
        }
    }

    @Override
    public IndividualPrompt saveIndividualPrompt(String promptText, String b64Json, UUID userId) {
        var promptEntity = createPromptEntity(b64Json,promptText, true,true);
        var customerEntity = customerEntityRepository.findByExternalCustomerId(userId);
        if (customerEntity.isPresent()) {
            promptEntity.setCustomerEntity(customerEntity.get());
        } else {
            var newCustomerEntity = new CustomerEntity();
            newCustomerEntity.setExternalCustomerId(userId);
            newCustomerEntity.setCountry(CZECH_REPUBLIC_COUNTRY_CODE);
            promptEntity.setCustomerEntity(newCustomerEntity);
        }
        var savedPromptEntity = promptEntityRepository.save(promptEntity);
        return promptEntity2IndividualPromptMapper.map(savedPromptEntity);
    }

    private List<TagEntity> getTags(List<String> userListTag){
        return tagEntityRepository.findAllByTagNameIn(userListTag);
    }

    @Override
    public Prompt savePromptPictureTemporarily(String promptText, String b64Json, UUID userId) {
        var promptEntity = createPromptEntity(b64Json,promptText, false, false);
        var customerEntity = customerEntityRepository.findByExternalCustomerId(userId);
        if (customerEntity.isPresent()) {
            promptEntity.setCustomerEntity(customerEntity.get());
        } else {
            var newCustomerEntity = new CustomerEntity();
            newCustomerEntity.setExternalCustomerId(userId);
            newCustomerEntity.setCountry(CZECH_REPUBLIC_COUNTRY_CODE);
            promptEntity.setCustomerEntity(newCustomerEntity);
        }
        var savedPromptEntity = promptEntityRepository.save(promptEntity);
        return promptEntity2PromptMapper.toPrompt(savedPromptEntity);
    }

    @Override
    public List<Prompt> getListOfPrompt(PromptFilter promptFilter) {

        var listOfTag = promptFilter.getListOfTag();
        var promptEntities = promptEntityRepository.getPromptEntityBySavedIsTrueAndIndividualIsFalse();

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
        var promptEntity = promptEntityRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(PROMPT_NOT_FOUND));

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

    @Override
    public List<Prompt> getListOfAllPrompts() {
        var listOfAllPromptEntity = promptEntityRepository.findAll();
        return promptEntity2PromptMapper.toListOfPrompt(listOfAllPromptEntity);
    }

    private PromptEntity createPromptEntity(String b64TextJson, String promptText, boolean saved, boolean individual){
        var promptEntity = new PromptEntity();
        var promptPictureEntity = new PromptPictureEntity();
        promptPictureEntity.setText(promptText);
        promptPictureEntity.setFileB64JsonText(b64TextJson);
        promptEntity.setPromptPictureEntity(promptPictureEntity);
        promptEntity.setSaved(saved);
        promptEntity.setIndividual(individual);

        return promptEntity;
    }

}
