package com.mvc.counsel.info.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.counsel.info.dao.SchedulingMapper;
import com.mvc.counsel.info.vo.CounselInfoVO;

@Service
public class SchedulingServiceImpl implements SchedulingService {

	@Autowired
	SchedulingMapper schedulingDAO;

	static List<String> slangWord;
	static String masking;

	/*
	 * 비속어 및 마스킹 등록 
	 */
	public void slangWordAdd() {
		slangWord = new ArrayList<String>();
		slangWord.add("뻔뻔");
		slangWord.add("싸가지");
		slangWord.add("건성");
		slangWord.add("말투");
		slangWord.add("찌질");
		
		masking = "*****";
	}

	/*
	 * 비속어 마스킹 처리
	 */
	@Override
	public void searchSlangWord() {
		// 모든 리스트 받고
		List<CounselInfoVO> counselList = schedulingDAO.counselList();
		if (slangWord == null) {
			slangWordAdd();
		}
		for (CounselInfoVO vo : counselList) {
			String counsel_title = vo.getCounsel_title();
			String counsel_content = vo.getCounsel_content();

			for (String word : slangWord) {
				int counsel_titleIndex = counsel_title.indexOf(word);
				int counsel_contentIndex = counsel_content.indexOf(word);
				/*
				 * 제목, 타이틀 중 하나라도 비속어 존재.
				 */
				if (counsel_titleIndex * counsel_contentIndex != 1) {
					if (counsel_title.indexOf(word) != -1)
						vo.setCounsel_title(counsel_title.replace(word, masking));
					if (counsel_content.indexOf(word) != -1)
						vo.setCounsel_content(counsel_content.replace(word, masking));
					schedulingDAO.changeWordUpdate(vo);
				}
			}
		}

	}

	/*
	 * 특정 날이 지난 게시물 검색
	 */
	@Override
	public List<CounselInfoVO> daysPassSearch(int day) {
		return schedulingDAO.daysPassSearch(day);
	}

	/*
	 * 특정 날이 지난 게시물 삭제
	 */
	@Override
	public void daysPassDelete(List<CounselInfoVO> list) {
		schedulingDAO.daysPassDelete(list);
	}

}
