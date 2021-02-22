package com.mvc.counsel.info.service;

import java.util.List;

import com.mvc.counsel.info.vo.CounselInfoVO;

public interface SchedulingService {
	/*
	 * 비속어 마스킹 처리
	 */
	public void searchSlangWord();

	/*
	 * 5일이 지난 게시물 검색
	 */
	public List<CounselInfoVO> daysPassSearch(int day);

	/*
	 * 특정 날이 지난 게시물 삭제
	 */
	public void daysPassDelete(List<CounselInfoVO> list);

}
