package com.mvc.counsel.dao.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mvc.counsel.info.dao.CounselInfoMapper;
import com.mvc.counsel.info.service.CounselInfoService;
import com.mvc.counsel.info.vo.CounselInfoVO;
import com.mvc.counsel.info.vo.CounselLockVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:comf/*.xml",
		"file:src/main/webapp/WEB-INF/views/servlet-context.xml"})
public class CounselListDAOTest {

	@Autowired
	CounselInfoMapper counselListDAO;
	
	@Autowired
	CounselInfoService counselInfoService;

	static final Logger logger = LoggerFactory.getLogger(CounselListDAOTest.class);

	/*
	 * 게시물 작성 테스트
	 */
	@Test
	public void postCreateTest() {
		CounselInfoVO vo = new CounselInfoVO();
		vo.setCounsel_title("title test1");
		vo.setCounsel_content("cotent test1");
		vo.setCounsel_userId("id1");
		CounselLockVO lvo = new CounselLockVO(); 
		lvo.setLockPassword("1234");
		vo.setCounsel_userId("admin");
		
		try {
			counselListDAO.counselInfoCreate(vo);
			counselListDAO.counselLockAdd(lvo);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("오류 :" + e);

		}
	}

	/*
	 * 게시물 목록 test
	 */
	@Test
	public void postListTest() {
		List<CounselInfoVO> vo = counselInfoService.counselInfoList();
		for (CounselInfoVO e : vo)
			logger.info(e.getCounsel_title());

	}

	/*
	 * 게시물 수정 test
	 */
	@Test
	public void postUpdate() {
		CounselInfoVO vo = new CounselInfoVO();
		vo.setCounsel_title("title test update");
		vo.setCounsel_content("cotent test update");
		vo.setSeq_counsel(1);
		counselListDAO.counselInfoUpdate(vo);
		vo = counselListDAO.counselInfoDetail(1);
		logger.info(vo.getCounsel_title());
	}

}
