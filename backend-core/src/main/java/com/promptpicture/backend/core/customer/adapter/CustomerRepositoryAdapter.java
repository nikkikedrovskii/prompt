package com.promptpicture.backend.core.customer.adapter;

import java.util.UUID;

public interface CustomerRepositoryAdapter {

    String findCountryCodeByExternalCustomerId(UUID externalCustomerId);
}
