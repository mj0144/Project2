package com.mvc.counsel.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.counsel.info.dao.CounselReplyMapper;
import com.mvc.counsel.info.vo.CounselReplyVO;

@Service
public class CounselReplyServiceImpl implements CounselReplyService{

	@Autowired
	private CounselReplyMapper counselReplyDAO;
	
	/*
	 * 댓글 등록
	 */
	@Override
	public void replyCreate(CounselReplyVO replyVO) {
		counselReplyDAO.replyCreate(replyVO);
	}
	/*
	 * 댓글 목록
	 */
	@Override
	public List<CounselReplyVO> replyList(int seq_counsel){
		return counselReplyDAO.replyList(seq_counsel);
	}
	/*
	 * 댓글 수정
	 */
	@Override
	public void replyUpdate(CounselReplyVO replyVO) {
	}
	
	/*
	 * 댓글 삭제
	 */
	@Override
	public void replyDelete(CounselReplyVO replyVO) {
	}
}
