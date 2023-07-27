package com.promptpicture.backend.core.prompt.use_case;

import com.promptpicture.backend.core.customer.adapter.CustomerRepositoryAdapter;
import com.promptpicture.backend.core.prompt.adapter.PromptRepositoryAdapter;
import com.promptpicture.backend.core.prompt.domain.Prompt;
import com.promptpicture.backend.core.vat.adapter.VatRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPromptDetailUseCase {

    private final PromptRepositoryAdapter promptRepositoryAdapter;
    private final VatRepositoryAdapter vatRepositoryAdapter;
    private final CustomerRepositoryAdapter customerRepositoryAdapter;

    public Prompt execute(Long id) {

        return promptRepositoryAdapter.findPromptById(id);
    }

}
