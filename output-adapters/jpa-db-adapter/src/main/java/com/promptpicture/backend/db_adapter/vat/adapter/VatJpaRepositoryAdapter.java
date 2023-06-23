package com.promptpicture.backend.db_adapter.vat.adapter;

import com.promptpicture.backend.core.exception.business.BadRequestException;
import com.promptpicture.backend.core.vat.adapter.VatRepositoryAdapter;
import com.promptpicture.backend.core.vat.domain.Vat;
import com.promptpicture.backend.db_adapter.vat.mapper.VatEntity2VatMapper;
import com.promptpicture.backend.jpa.vat.repository.VatEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.promptpicture.backend.core.exception.error_code.ErrorCode.PROMPT_NOT_FOUND;
import static com.promptpicture.backend.core.exception.error_code.ErrorCode.VAT_NOT_EXIST;

@Component
@RequiredArgsConstructor
public class VatJpaRepositoryAdapter implements VatRepositoryAdapter {

    private final VatEntityRepository vatEntityRepository;
    private final VatEntity2VatMapper vatEntity2VatMapper;

    @Override
    public Vat getVatByCountryCode(String countryCode) {
        var vatEntity = vatEntityRepository.findVatEntityByCountryCode(countryCode)
                .orElseThrow(() -> new BadRequestException(VAT_NOT_EXIST));
        return vatEntity2VatMapper.map(vatEntity);
    }
}
