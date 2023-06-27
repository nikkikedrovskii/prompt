package com.promptpicture.backend.entrypoint.quartz.cart.job;

import com.promptpicture.backend.core.cart.use_case.ClearShadowCartUseCase;
import lombok.RequiredArgsConstructor;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@DisallowConcurrentExecution
public class ClearShadowCartJob implements Job {

    private final ClearShadowCartUseCase clearShadowCartUseCase;

    @Override
    public void execute(JobExecutionContext context) {
        clearShadowCartUseCase.execute();
    }
}
