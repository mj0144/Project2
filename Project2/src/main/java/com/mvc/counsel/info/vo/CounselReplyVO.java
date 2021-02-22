package com.mvc.counsel.info.vo;

public class CounselReplyVO {
	/*
	 * counse_reply 테이블. 댓글 정보
	 */
	private int seq_reply;	//댓글 번호
	private String reply_content;	//댓글 내용
	private String reply_date;	//댓글 작성일
	private CounselInfoVO counselInfoVO;	//게시글 테이블

	/*
	 * getter, setter
	 */
	public CounselInfoVO getCounselInfoVO() {
		return counselInfoVO;
	}

	public void setCounselInfoVO(CounselInfoVO counselInfoVO) {
		this.counselInfoVO = counselInfoVO;
	}

	public int getSeq_reply() {
		return seq_reply;
	}

	public void setSeq_reply(int seq_reply) {
		this.seq_reply = seq_reply;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public String getReply_date() {
		return reply_date;
	}

	//작성일 시간 뒤에 .ms 제거
	public void setReply_date(String reply_date) {
		reply_date = reply_date.substring(0, reply_date.lastIndexOf("."));
		this.reply_date = reply_date;
	}

	/*
	 * mapper에서 게시물 번호 가져올 getter
	 */
	public int getSeq_counsel() {
		int seq_counsel = this.getCounselInfoVO().getSeq_counsel();
		return seq_counsel;
	}

}
