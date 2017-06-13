package com.whl.schedule;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

/**
 * 
 * description: 自定义定时任务,可以自动切换cron，任务执行计划
 * 
 * @author whling
 * @date 2017年6月12日 下午1:16:43
 *
 */

@Configuration
@EnableScheduling
public class ScheduleTask implements SchedulingConfigurer {

	public static String cron;

	// @Scheduled(cron="*/5 * * * * *")
	public void reportCurrentTime() {
		System.out.println(DateTime.now().toString("HH:mm:ss"));
	}
	/*
	 * 使用了@Scheduled(fixedRate = 5000) 注解来定义每过5秒执行的任务，对于@Scheduled的使用可以总结如下几种方式：
	 * 
	 * @Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
	 * 
	 * @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
	 * 
	 * @Scheduled(initialDelay=1000, fixedRate=5000)
	 * ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
	 * 
	 * @Scheduled(cron="
	 *//* 5 * * * * *") ：通过cron表达式定义规则 */

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				// 任务逻辑代码部分.
				System.out.println("TaskCronChange task is running ... " + new Date());
			}
		};
		Trigger trigger = new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				// 任务触发，可修改任务的执行周期.
				CronTrigger trigger = new CronTrigger(cron);
				Date nextExec = trigger.nextExecutionTime(triggerContext);
				return nextExec;
			}
		};
		taskRegistrar.addTriggerTask(task, trigger);
	}

	public ScheduleTask() {
		cron = "*/5 * * * * *";
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					// 让线程睡眠 15秒.
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 修改为：每10秒执行一次.
				cron = "0/10 * * * * *";
				System.err.println("cron change to:" + cron);

			}
		}).start();
	}
}
