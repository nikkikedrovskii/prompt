package com.promptpicture.backend.db_adapter.order.mapper;

import com.promptpicture.backend.core.order.domain.PromptInOrder;
import com.promptpicture.backend.jpa.order.entity.PromptInOrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PromptInOrderEntity2PromptInOrderMapper {

    @Mapping(target = "b64Json", source = "promptOrderDataEntity.promptData")
    PromptInOrder map(PromptInOrderEntity promptInOrderEntity);

    List<PromptInOrder> mapList(List<PromptInOrderEntity> promptInOrderEntity);
}
