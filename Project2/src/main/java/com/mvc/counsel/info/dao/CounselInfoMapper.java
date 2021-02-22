package com.mvc.counsel.info.dao;

import java.util.List;
import java.util.Map;

import com.mvc.counsel.comm.MapperScanAnnotation;
import com.mvc.counsel.info.vo.CounselInfoVO;
import com.mvc.counsel.info.vo.CounselLockVO;

@MapperScanAnnotation
public interface CounselInfoMapper {
	/*
	 * 게시물 등록
	 */
	public void counselInfoCreate(CounselInfoVO vo);

	/*
	 * 게시물 목록
	 */
	// @Select("select * from COUNSEL_INFO ORDER BY counsel_date asc")
	public List<CounselInfoVO> counselInfoList();

	/*
	 * 게시물 수정 update
	 */
	public void counselInfoUpdate(CounselInfoVO vo);

	/*
	 * 게시글 삭제
	 */
	public int counselInfoDelete(int seq_counsel);

	/*
	 * 글쓴이 검색
	 */
	public String counselOneUserId(int seq_counsel);

	/*
	 * 게시글 자세히 보기
	 */
	public CounselInfoVO counselInfoDetail(int seq_counsel);

	/*
	 * 검색
	 */
	public List<CounselInfoVO> searchList(Map<String, String> map);

	/*
	 * 비밀글 추가
	 */
	public void counselLockAdd(CounselLockVO counselLockVO);

	/*
	 * 비밀글 목록
	 */
	public List<CounselLockVO> counselLockList();

	/*
	 * 비밀글 체크
	 */
	public int accessLock(CounselLockVO counselLockVO);
}
