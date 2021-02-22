package com.mvc.counsel.info.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mvc.counsel.info.exception.LockDeniedException;
import com.mvc.counsel.info.exception.UserURLDeniedException;
import com.mvc.counsel.info.vo.CounselInfoVO;
import com.mvc.counsel.info.vo.CounselLockVO;

public interface CounselInfoService {
	/*
	 * 게시글 등록
	 */
	public void counselInfoCreate(CounselInfoVO counselInfoVO, CounselLockVO counselLockVO, String id);

	/*
	 * 게시글 목록
	 */
	public List<CounselInfoVO> counselInfoList();

	/*
	 * 게시글 수정
	 */
	public void counselInfoUpdate(CounselInfoVO vo, HttpServletRequest request) throws UserURLDeniedException;

	/*
	 * 게시글 삭제
	 */
	public int counselInfoDelete(int seq_counsel, HttpServletRequest request) throws UserURLDeniedException;

	/*
	 * 게시글 읽기
	 */
	public CounselInfoVO counselInfoDetail(int seq_counsel, String lockChk) throws UserURLDeniedException;
	
	/*
	 * 게시글 읽기 overloading
	 */
	public CounselInfoVO counselInfoDetail(int seq_counsel);
 
	/*
	 * 검색
	 */
	public List<CounselInfoVO> searchList(String search_option, String search_query);
	
	/*
	 * 비밀글 목록
	 */
	public List<CounselLockVO> counselLockList();
	
	/*
	 * 비밀글 체크
	 */
	public void accessLock(CounselLockVO counselLockVO) throws LockDeniedException;
	
	/*
	 * 사용자 일치 여부
	 */
	public boolean checkAuth(String user, HttpServletRequest request);

}
