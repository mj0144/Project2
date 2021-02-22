package com.mvc.counsel.info.dao;

import java.util.List;

import com.mvc.counsel.comm.MapperScanAnnotation;
import com.mvc.counsel.info.vo.CounselReplyVO;

@MapperScanAnnotation
public interface CounselReplyMapper {
	/*
	 * 댓글 작성
	 */
	public void replyCreate(CounselReplyVO replyVO);

	/*
	 * 댓글 목록
	 */
	public List<CounselReplyVO> replyList(int seq_counsel);

}
