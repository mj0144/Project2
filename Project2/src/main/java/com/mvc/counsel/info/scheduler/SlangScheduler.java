package com.mvc.counsel.info.scheduler;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mvc.counsel.info.service.SchedulingService;

/*
 * Job : 비속어 마스킹 
 */
public class SlangScheduler extends Scheduler {
	
	static final Logger logger = LoggerFactory.getLogger(SlangScheduler.class);

	@Override
	protected void executeJob(JobExecutionContext context) {
		logger.info("비속어 마스킹 스케줄러 시작");
		//ApplicationContext에서 사용할 bean 인스턴스 가져오기.
		SchedulingService schedulingService= (SchedulingService) super.getBean("schedulingServiceImpl");
		
		//비속어 처리
		schedulingService.searchSlangWord();
	}
}
