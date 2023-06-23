package com.promptpicture.backend.core.prompt.use_case;

import com.promptpicture.backend.core.customer.adapter.CustomerRepositoryAdapter;
import com.promptpicture.backend.core.prompt.adapter.PromptRepositoryAdapter;
import com.promptpicture.backend.core.prompt.domain.Prompt;
import com.promptpicture.backend.core.vat.adapter.VatRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetPromptDetailUseCase {

    private final PromptRepositoryAdapter promptRepositoryAdapter;
    private final VatRepositoryAdapter vatRepositoryAdapter;
    private final CustomerRepositoryAdapter customerRepositoryAdapter;

    public Prompt execute(Long id, UUID externalCustomerId) {

        var prompt = promptRepositoryAdapter.findPromptById(id);
        var vatRate = getVatRate(externalCustomerId);
        var promptPriceWithVat = getPromptPriceWithVat(prompt,vatRate);
        prompt.setPriceWithVat(promptPriceWithVat);

        return prompt;
    }

    private BigDecimal getVatRate(UUID externalCustomerId){
        var countryCode = customerRepositoryAdapter.findCountryCodeByExternalCustomerId(externalCustomerId);
        return vatRepositoryAdapter.getVatByCountryCode(countryCode).getVatRate();
    }

    private BigDecimal getPromptPriceWithVat(Prompt prompt, BigDecimal vatRate){
        var price = prompt.getPrice();
        var fullRate = new BigDecimal(1).subtract(vatRate.divide(new BigDecimal(100)));
        return price.divide(fullRate,2, RoundingMode.HALF_EVEN);
    }

}
