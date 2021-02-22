package com.mvc.counsel.dao.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mvc.counsel.info.dao.CounselInfoMapper;
import com.mvc.counsel.info.vo.CounselInfoVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class SearchTest {

	private static final Logger logger = LoggerFactory.getLogger(SearchTest.class); 
	// @formatter:on

	@Autowired
	CounselInfoMapper counselInfoDAO;
	
	@Test
	public void searchTest() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", "1");
		map.put("searchText", "ee");
		List<CounselInfoVO> list = counselInfoDAO.searchList(map);
		for(CounselInfoVO vo : list)
			logger.info(vo.getCounsel_title());
	}
	
	
	
	@Test
	public void time() {
			String time = "2020-12-30 09:29:11.0";
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			time = format.format(date);
			
			logger.info("시간  : " +time);
			
		}
	}

