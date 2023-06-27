package com.promptpicture.backend.entrypoint.quartz.prompt.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ClearPromptHistoryJobIdentifiers {

    public static final String CLEAR_PROMPT_HISTORY = "CLEAR_PROMPT_HISTORY";
    public static final String CLEAR_PROMPT_HISTORY_JOB = "clearPromptHistoryJob";
    public static final String CLEAR_PROMPT_HISTORY_TRIGGER= "clearPromptHistoryTrigger";

}
