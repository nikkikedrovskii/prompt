package com.promptpicture.backend.entrypoint.quartz.cart.scheduler;

import com.promptpicture.backend.entrypoint.quartz.cart.job.ClearShadowCartJob;
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

import static com.promptpicture.backend.entrypoint.quartz.cart.common.ClearShadowCartJobIdentifiers.CLEAR_SHADOW_CART;
import static com.promptpicture.backend.entrypoint.quartz.cart.common.ClearShadowCartJobIdentifiers.CLEAR_SHADOW_CART_JOB;
import static com.promptpicture.backend.entrypoint.quartz.cart.common.ClearShadowCartJobIdentifiers.CLEAR_SHADOW_CART_TRIGGER;

@Component
@RequiredArgsConstructor
public class ClearShadowCartJobScheduler {

    private static final String CLEAR_SHADOW_CART_CRON_EXPRESSION_PATTERN = "0 0 1 * * ?";  // # Every day at 1.AM
    private final Scheduler scheduler;

    @PostConstruct
    public void initScheduling() throws SchedulerException, ParseException {

        var jobDetail = createJobDetail();
        var cronTrigger = createCroneTrigger(jobDetail);
        scheduler.scheduleJob(jobDetail, cronTrigger);


    }

    private JobDetail createJobDetail() {

        var jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(ClearShadowCartJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setName(CLEAR_SHADOW_CART_JOB);
        jobDetailFactory
                .setGroup(CLEAR_SHADOW_CART);
        jobDetailFactory.setDescription(
                "Delete cart if it is older than two days.");
        jobDetailFactory.afterPropertiesSet();
        return jobDetailFactory.getObject();
    }

    private CronTrigger createCroneTrigger(JobDetail jobDetail) throws ParseException {

        var triggerFactory = new CronTriggerFactoryBean();
        triggerFactory.setName(CLEAR_SHADOW_CART_TRIGGER);
        triggerFactory.setJobDetail(jobDetail);
        triggerFactory.setCronExpression(CLEAR_SHADOW_CART_CRON_EXPRESSION_PATTERN);
        triggerFactory.afterPropertiesSet();
        return triggerFactory.getObject();

    }
}
