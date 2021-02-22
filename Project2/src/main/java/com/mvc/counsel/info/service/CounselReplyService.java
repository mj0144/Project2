package com.mvc.counsel.info.service;

import java.util.List;

import com.mvc.counsel.info.vo.CounselReplyVO;

public interface CounselReplyService {
	public void replyCreate(CounselReplyVO replyVO);

	public List<CounselReplyVO> replyList(int seq_counsel);

	public void replyUpdate(CounselReplyVO replyVO);

	public void replyDelete(CounselReplyVO replyVO);

}
