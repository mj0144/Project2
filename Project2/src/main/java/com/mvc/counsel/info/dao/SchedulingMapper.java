package com.mvc.counsel.info.dao;

import java.util.List;

import com.mvc.counsel.comm.MapperScanAnnotation;
import com.mvc.counsel.info.vo.CounselInfoVO;

@MapperScanAnnotation
public interface SchedulingMapper {
	/*
	 * 모든 게시물 목록 listup
	 */
	public List<CounselInfoVO> counselList();
	/*
	 * 바뀐 비속어 update
	 */
	public void changeWordUpdate(CounselInfoVO vo);
	/*
	 * 5일이 지난 게시물 검색
	 */
	public List<CounselInfoVO> daysPassSearch(int day);
	/*
	 * 5일이 지난 게시물 삭제
	 */
	public void daysPassDelete(List<CounselInfoVO> list);
	
}
