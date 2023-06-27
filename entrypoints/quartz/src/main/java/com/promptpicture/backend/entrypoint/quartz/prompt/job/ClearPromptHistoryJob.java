package com.promptpicture.backend.entrypoint.quartz.prompt.job;

import com.promptpicture.backend.core.prompt.use_case.ClearPromptHistoryUseCase;
import lombok.RequiredArgsConstructor;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@DisallowConcurrentExecution
public class ClearPromptHistoryJob implements Job {

    private final ClearPromptHistoryUseCase clearPromptHistoryUseCase;

    @Override
    public void execute(JobExecutionContext context)  {
        clearPromptHistoryUseCase.execute();
    }
}
