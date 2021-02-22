package com.mvc.counsel.scheduler.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mvc.counsel.info.dao.SchedulingMapper;
import com.mvc.counsel.info.service.CounselInfoService;
import com.mvc.counsel.info.service.SchedulingService;
import com.mvc.counsel.info.vo.CounselInfoVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/quartz-context.xml"})
public class SchedulerTest {
	@Autowired
	SchedulingService schedulingService;
	@Autowired
	CounselInfoService counselInfoService;
	@Autowired
	SchedulingMapper schedulingDAO;

	@Test
	public void slangchedulerTest() {
		schedulingService.searchSlangWord();
		List<CounselInfoVO> vo = counselInfoService.counselInfoList();
		for(CounselInfoVO e : vo) {
			System.out.println(e.getCounsel_title());
		}
	}
	
	@Test
	public void deleteSchedulerTest() {
		List<CounselInfoVO> counselList = schedulingService.daysPassSearch(1);
		for(CounselInfoVO vo : counselList) {
			System.out.println(vo.getSeq_counsel());
		}

		if(counselList.size() != 0) {
			schedulingDAO.daysPassDelete(counselList);
			System.out.println("삭제완료");
		}else {
			System.out.println("삭제할 게시물 없음");
		}
	}
	


}
