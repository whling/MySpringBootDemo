package com.whl.controller;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DynamicTask {
	//使用Java配置的方式注入，在RegisterBeanFactory中
	@Autowired
	private ThreadPoolTaskScheduler threadPoolTaskScheduler;

	private ScheduledFuture<?> future;

	

	@RequestMapping("/startCron")
	public String startCron() {
		future = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger("0/5 * * * * *"));
		System.out.println("DynamicTask.startCron()");
		return "startCron";
	}

	@RequestMapping("/stopCron")
	public String stopCron() {
		if (future != null) {
			future.cancel(true);
		}
		System.out.println("DynamicTask.stopCron()");
		return "stopCron";
	}

	@RequestMapping("/changeCron10")
	public String startCron10() {
		stopCron();// 先停止，在开启.
		future = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger("*/10 * * * * *"));
		System.out.println("DynamicTask.startCron10()");
		return "changeCron10";
	}

	private class MyRunnable implements Runnable {
		@Override
		public void run() {
			System.out.println("DynamicTask.MyRunnable.run()，" + new Date());
		}
	}
}
