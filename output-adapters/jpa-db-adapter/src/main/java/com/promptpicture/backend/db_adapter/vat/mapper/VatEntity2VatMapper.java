package com.promptpicture.backend.db_adapter.vat.mapper;

import com.promptpicture.backend.core.vat.domain.Vat;
import com.promptpicture.backend.jpa.vat.entity.VatEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VatEntity2VatMapper {

    Vat map(VatEntity vatEntity);
}
