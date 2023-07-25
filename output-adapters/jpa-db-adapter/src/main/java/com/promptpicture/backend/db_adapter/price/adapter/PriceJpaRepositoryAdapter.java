package com.promptpicture.backend.db_adapter.price.adapter;

import com.promptpicture.backend.core.exception.business.BadRequestException;
import com.promptpicture.backend.core.price.adapter.PriceRepositoryAdapter;
import com.promptpicture.backend.core.price.domain.Price;
import com.promptpicture.backend.db_adapter.price.mapper.PriceEntity2PriceMapper;
import com.promptpicture.backend.jpa.price.repository.PriceEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.promptpicture.backend.core.exception.error_code.ErrorCode.PRICE_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class PriceJpaRepositoryAdapter implements PriceRepositoryAdapter {

    private final PriceEntityRepository priceEntityRepository;
    private final PriceEntity2PriceMapper priceEntity2PriceMapper;

    @Override
    public Price getPriceByResolution(String resolution) {
        var price = priceEntityRepository.findPriceEntityByResolution(resolution)
                .orElseThrow(() -> new BadRequestException(PRICE_NOT_FOUND));
        return priceEntity2PriceMapper.map(price);
    }
}
