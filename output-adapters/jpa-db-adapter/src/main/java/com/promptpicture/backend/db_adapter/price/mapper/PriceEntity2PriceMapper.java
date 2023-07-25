package com.promptpicture.backend.db_adapter.price.mapper;

import com.promptpicture.backend.core.price.domain.Price;
import com.promptpicture.backend.jpa.price.entity.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceEntity2PriceMapper {

    Price map(PriceEntity from);
}
