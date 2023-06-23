package com.promptpicture.backend.core.vat.adapter;

import com.promptpicture.backend.core.vat.domain.Vat;

public interface VatRepositoryAdapter {
    Vat getVatByCountryCode(String countryCode);
}
