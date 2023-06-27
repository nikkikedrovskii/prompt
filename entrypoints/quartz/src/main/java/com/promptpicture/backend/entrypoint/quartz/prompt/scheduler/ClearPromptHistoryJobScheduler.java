package com.promptpicture.backend.entrypoint.quartz.prompt.scheduler;

import com.promptpicture.backend.entrypoint.quartz.cart.job.ClearShadowCartJob;
import com.promptpicture.backend.entrypoint.quartz.prompt.job.ClearPromptHistoryJob;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;

import static com.promptpicture.backend.entrypoint.quartz.prompt.common.ClearPromptHistoryJobIdentifiers.CLEAR_PROMPT_HISTORY;
import static com.promptpicture.backend.entrypoint.quartz.prompt.common.ClearPromptHistoryJobIdentifiers.CLEAR_PROMPT_HISTORY_JOB;
import static com.promptpicture.backend.entrypoint.quartz.prompt.common.ClearPromptHistoryJobIdentifiers.CLEAR_PROMPT_HISTORY_TRIGGER;

@Component
@RequiredArgsConstructor
public class ClearPromptHistoryJobScheduler {

    private static final String CLEAR_PROMPT_HISTORY_CRON_EXPRESSION_PATTERN = "0 0 */12 ? * *"; // Every twelve hours
    private final Scheduler scheduler;

    @PostConstruct
    public void initScheduling() throws SchedulerException, ParseException {

        var jobDetail = createJobDetail();
        var cronTrigger = createCroneTrigger(jobDetail);
        scheduler.scheduleJob(jobDetail, cronTrigger);


    }

    private JobDetail createJobDetail() {

        var jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(ClearPromptHistoryJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setName(CLEAR_PROMPT_HISTORY_JOB);
        jobDetailFactory
                .setGroup(CLEAR_PROMPT_HISTORY);
        jobDetailFactory.setDescription(
                "Delete prompt if it is older than 12 hours.");
        jobDetailFactory.afterPropertiesSet();
        return jobDetailFactory.getObject();
    }

    private CronTrigger createCroneTrigger(JobDetail jobDetail) throws ParseException {

        var triggerFactory = new CronTriggerFactoryBean();
        triggerFactory.setName(CLEAR_PROMPT_HISTORY_TRIGGER);
        triggerFactory.setJobDetail(jobDetail);
        triggerFactory.setCronExpression(CLEAR_PROMPT_HISTORY_CRON_EXPRESSION_PATTERN);
        triggerFactory.afterPropertiesSet();
        return triggerFactory.getObject();

    }

}
