package com.mvc.counsel.info.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.mvc.counsel.info.service.SchedulingService;
import com.mvc.counsel.info.vo.CounselInfoVO;

/*
 * Job : 날짜 지난 게시글 삭제
 */
public class DeleteScheduler{

	static final Logger logger = LoggerFactory.getLogger(DeleteScheduler.class);

	@Autowired
	SchedulingService schedulingService;
	
	
	//@Scheduled(cron = "0/10 * * ? * *")
	protected void executeJob(){
		
		logger.info("날짜지난 게시글 삭제 스케줄러 시작");
		
		/*
		 * 지난 일수
		 * -1 : 하루가 지남
		 */
		int day = 1;
		
		/*
		 * 일수(day)만큼 지난 게시물 검색
		 */
		List<CounselInfoVO> dayPassCounselList = schedulingService.daysPassSearch(day);

		/*
		 *  해당 게시물이 존재한다면 삭제
		 */	
		int countDeleteCounsel = dayPassCounselList.size();
		if(countDeleteCounsel != 0) {
			schedulingService.daysPassDelete(dayPassCounselList);
			logger.info(countDeleteCounsel+"개 게시물 삭제완료");
		}else {
			logger.info("삭제할 게시물 없음");
		}
		
	}

}
