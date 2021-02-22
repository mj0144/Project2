package com.mvc.counsel.dao.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mvc.counsel.info.dao.SchedulingMapper;
import com.mvc.counsel.info.vo.CounselInfoVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:comf/*.xml",
"file:src/main/webapp/WEB-INF/views/servlet-context.xml"})
public class JavaTest {

	static final Logger logger = LoggerFactory.getLogger(JavaTest.class);

	@Autowired
	SchedulingMapper schedulingDAO;

	@Test
	public void testSlangMap() {
		Map<String, String> map = new HashMap<String, String>();
		String[] slangWord = { "뻔", "사" };
		String masking = "****";

		// map에 값넣기.
		for (String e : slangWord) {
			map.put(e, masking);
		}

		List<CounselInfoVO> counselList = schedulingDAO.counselList();

		for (CounselInfoVO vo : counselList) {
			String counsel_title = vo.getCounsel_title();
			String counsel_content = vo.getCounsel_content();

			for (String word : map.keySet()) {
				int counsel_titleIndex = counsel_title.indexOf(word);
				int counsel_contentIndex = counsel_content.indexOf(word);
				/*
				 * 제목, 타이틀 중 하나라도 비속어 존재.
				 */
				if (counsel_titleIndex * counsel_contentIndex != 1) {
					if (counsel_title.indexOf(word) != -1)
						vo.setCounsel_title(counsel_title.replace(word, map.get(word)));
					if (counsel_content.indexOf(word) != -1)
						vo.setCounsel_content(counsel_content.replace(word, map.get(word)));
					schedulingDAO.changeWordUpdate(vo);
				}
			}
		}
	}


	@Test
	public void arrayStreamTest() {
		// 배열을 리스트 처럼 사용
		List<String> list = Arrays.asList("a", "b", "c");

		// 오류남. asList 쓴 이후는 add못씀.
		// list.add("r");

		System.out.println(list); // (a,b,c) 출력
		
		
		//이렇게하면 list2는 array의 주소값을 참조함.
		String[] array = {"d", "e", "f"};
		List<String> list2  =Arrays.asList(array);
		System.out.println("원래 배열 : " + array.hashCode());
		System.out.println("새로만든 list : " + list2.hashCode());
		
		
		
	}
	
	
	
	



	/*
	 * 비속어 마스킹 처리
	 */
	@Test
	public void searchSlangWord() {
		// 모든 리스트 받고
		List<CounselInfoVO> counselList = schedulingDAO.counselList();
		List<String> slangWord = Arrays.asList("뻔뻔", "싸가지", "건성", "말투", "찌질");
		String masking = "****";


		for (CounselInfoVO vo : counselList) {
			String counsel_title = vo.getCounsel_title();
			String counsel_content = vo.getCounsel_content();

			for (String word : slangWord) {
				logger.info(word);
				int counsel_titleIndex = counsel_title.indexOf(word);
				int counsel_contentIndex = counsel_content.indexOf(word);
				/*
				 * 제목, 타이틀 중 하나라도 비속어 존재.
				 */
				logger.info(String.valueOf(counsel_titleIndex));
				logger.info(String.valueOf(counsel_contentIndex));
				if (counsel_titleIndex * counsel_contentIndex != 1) {
					if (counsel_title.indexOf(word) != -1)
						vo.setCounsel_title(counsel_title.replace(word, masking));
					if (counsel_content.indexOf(word) != -1)
						vo.setCounsel_content(counsel_content.replace(word, masking));
					logger.info("1");
					//schedulingDAO.changeWordUpdate(vo);
				}
			}
		}

	}
}
