package com.promptpicture.backend.core.price.adapter;

import com.promptpicture.backend.core.price.domain.Price;

public interface PriceRepositoryAdapter {

    Price getPriceByResolution(String resolution);

}
