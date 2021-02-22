package com.mvc.counsel.dao.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mvc.counsel.info.dao.CounselReplyMapper;
import com.mvc.counsel.info.vo.CounselInfoVO;
import com.mvc.counsel.info.vo.CounselReplyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CounselReplyDAOTest {
	@Autowired
	CounselReplyMapper counselReplyDAO;
	
	static final Logger logger = LoggerFactory.getLogger(CounselListDAOTest.class);
	
	
	/*
	 * 댓글 작성 test
	 */
	@Test
	public void replyCreate() {
		CounselReplyVO vo = new CounselReplyVO();
		CounselInfoVO counselInfoVO = new CounselInfoVO();
		vo.setCounselInfoVO(counselInfoVO);
		vo.setReply_content("댓글 테스트중");
		vo.getCounselInfoVO().setSeq_counsel(7);
		counselReplyDAO.replyCreate(vo);
	}
	
	/*
	 * 댓글 목록
	 */
	@Test
	public void replyList() {
		List<CounselReplyVO> list = counselReplyDAO.replyList(7);
		for(CounselReplyVO replyvo : list) {
			logger.info(replyvo.getReply_content());
		}
	}
}
