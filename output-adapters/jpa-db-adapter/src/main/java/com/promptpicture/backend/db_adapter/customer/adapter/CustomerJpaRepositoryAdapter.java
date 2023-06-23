package com.promptpicture.backend.db_adapter.customer.adapter;

import com.promptpicture.backend.core.customer.adapter.CustomerRepositoryAdapter;
import com.promptpicture.backend.jpa.customer.repository.CustomerEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerJpaRepositoryAdapter implements CustomerRepositoryAdapter {

    private final CustomerEntityRepository customerEntityRepository;
    private static final String CZECH_REPUBLIC_COUNTRY_CODE = "CZ";

    @Override
    public String findCountryCodeByExternalCustomerId(UUID externalCustomerId) {
        if (externalCustomerId != null) {
            var customerEntity = customerEntityRepository.findByExternalCustomerId(externalCustomerId);
            if (customerEntity.isPresent()) {
                return customerEntity.get().getCountry();
            }
            return CZECH_REPUBLIC_COUNTRY_CODE;
        }
        return CZECH_REPUBLIC_COUNTRY_CODE;
    }
}
