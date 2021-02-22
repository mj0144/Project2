package com.mvc.counsel.info.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/*
 * 스케줄러 실행 
 */
public abstract class Scheduler extends QuartzJobBean {

	static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

	/*
	 * bean을 사용하기 위해 ApplicationContext 불러오기.
	 */
	private ApplicationContext ctx;

	/*
	 * Trigger 발생 시, 스케줄러에 의해 실행될 메서드.
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		ctx = (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");

		/*
		 * ApplicationContext에 의해 등록된 Bean확인
		 */
//		for(String name : ctx.getBeanDefinitionNames()){
//            logger.info(name);
//        }

		/*
		 * 로직이 실행될 메서드
		 */
		executeJob(context);
	}

	protected abstract void executeJob(JobExecutionContext context) throws JobExecutionException;

	/*
	 * ApplicationContext에서 bean 가져오기.
	 */
	protected Object getBean(String beanId) {
		return ctx.getBean(beanId);
	}
}
