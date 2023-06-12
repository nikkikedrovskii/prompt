package com.promptpicture.backend.jpa.prompt.specification;

import com.promptpicture.backend.core.common.filter.BaseSpecification;
import com.promptpicture.backend.core.prompt.domain.PromptFilter;
import com.promptpicture.backend.jpa.prompt.entity.PromptEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
@RequiredArgsConstructor
public class PromptEntitySpecification extends BaseSpecification<PromptEntity, PromptFilter> {

    @Override
    public Specification<PromptEntity> getFilter(PromptFilter request) {
        return (root, query, cb) -> where(
                where(promptTagIn(request.getListOfTag()))
                        .and(promptAttributeEqual("saved",true)))
                .toPredicate(root, query, cb);
    }

    private Specification<PromptEntity> promptTagIn(List<String> listOfTag) {

        return (root, query, cb) -> {
            if (listOfTag.isEmpty()) {
                return null;
            }
            return root.get("promptTags").get("tagName").in(listOfTag);
        };
    }
}
