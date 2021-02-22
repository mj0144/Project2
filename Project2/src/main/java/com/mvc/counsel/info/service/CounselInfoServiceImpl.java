package com.mvc.counsel.info.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.counsel.info.dao.CounselInfoMapper;
import com.mvc.counsel.info.exception.LockDeniedException;
import com.mvc.counsel.info.exception.UserURLDeniedException;
import com.mvc.counsel.info.vo.CounselInfoVO;
import com.mvc.counsel.info.vo.CounselLockVO;

@Service
public class CounselInfoServiceImpl implements CounselInfoService {

	@Autowired
	private CounselInfoMapper counselInfoMapper;

	/*
	 * 게시글 등록
	 */
	@Override
	public void counselInfoCreate(CounselInfoVO counselInfoVO, CounselLockVO counselLockVO, String id) {
		// 글 작성자.
		counselInfoVO.setCounsel_userId(id);
		counselInfoMapper.counselInfoCreate(counselInfoVO);

		// 비밀글 추가. - 비밀번호가 비어있을지 않을 때.
		if (counselLockVO.getLockPassword() != "") {
			counselInfoMapper.counselLockAdd(counselLockVO);
		}
	}

	/*
	 * 게시글 목록 . 비밀글 여부 체크
	 */
	@Override
	public List<CounselInfoVO> counselInfoList() {
		List<CounselInfoVO> counselList = counselInfoMapper.counselInfoList();
		List<CounselLockVO> lockList = counselInfoMapper.counselLockList();
		xx: for (CounselLockVO counselLockVO : lockList) {
			for (CounselInfoVO counselInfoVO : counselList) {
				if (counselInfoVO.getSeq_counsel() == counselLockVO.getSeq_counsel()) {
					counselInfoVO.setLockchk(true);
					continue xx;
				} else
					counselInfoVO.setLockchk(false);
			}
		}
		return counselList;
	}

	/*
	 * 게시글 수정
	 */
	@Override
	public void counselInfoUpdate(CounselInfoVO vo, HttpServletRequest request) throws UserURLDeniedException {
		if (!checkAuth(vo.getCounsel_userId(), request)) {
			throw new UserURLDeniedException("사용자가 다릅니다");
		}
		counselInfoMapper.counselInfoUpdate(vo);
	}

	/*
	 * 게시글 삭제
	 */
	@Override
	public int counselInfoDelete(int seq_counsel, HttpServletRequest request) throws UserURLDeniedException {
		// 글쓴이 검색
		String user = counselInfoMapper.counselOneUserId(seq_counsel);
		if (!checkAuth(user, request)) {
			throw new UserURLDeniedException("사용자가 다릅니다");
		}
		return counselInfoMapper.counselInfoDelete(seq_counsel);
	}

	/*
	 * 게시글 정보 읽기 - 비밀글 체크
	 */
	@Override
	public CounselInfoVO counselInfoDetail(int seq_counsel, String lockChk) throws UserURLDeniedException {
		// 비밀글이 아니거나, 비밀글에 올바르게 접근했을 때.
		if (lockChk == null || lockChk.equals("true")) {
			return counselInfoMapper.counselInfoDetail(seq_counsel);
		} else {
			throw new UserURLDeniedException("접근할 수 없습니다.");
		}
	}

	/*
	 * 게시글 읽기 - overloading
	 */
	@Override
	public CounselInfoVO counselInfoDetail(int seq_counsel) {
		return counselInfoMapper.counselInfoDetail(seq_counsel);
	}

	/*
	 * 게시글 검색
	 */
	@Override
	public List<CounselInfoVO> searchList(String search_option, String search_query) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", search_option);
		map.put("searchQuery", search_query);
		return counselInfoMapper.searchList(map);
	}

	/*
	 * 비밀글 목록
	 */
	@Override
	public List<CounselLockVO> counselLockList() {
		return counselInfoMapper.counselLockList();
	}

	/*
	 * 비밀글 체크
	 */
	@Override
	public void accessLock(CounselLockVO counselLockVO) throws LockDeniedException {
		int chk = counselInfoMapper.accessLock(counselLockVO);
		// 일치하는 값이 업을 시, 예외발생.
		if (chk == 0)
			throw new LockDeniedException("잘못된 비밀번호 입니다.");
	}

	/*
	 * 사용자 일치 또는 admin 여부. true : 일치. false : 불일치.
	 */
	@Override
	public boolean checkAuth(String user, HttpServletRequest request) {
		if ((request.getUserPrincipal().getName()).equals(user) || request.isUserInRole("admin")) {
			return true;
		}
		return false;
	}

}
