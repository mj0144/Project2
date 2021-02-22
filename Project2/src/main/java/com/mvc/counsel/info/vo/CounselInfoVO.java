package com.mvc.counsel.info.vo;

public class CounselInfoVO {
	/*
	 * counsel_info 테이블. 게시글 정보
	 */
	private int seq_counsel; // 게시글 번호
	private String counsel_title; // 게시글 제목
	private String counsel_content; // 게시글 내용
	private int counsel_reply; // 게시글 댓글 번호
	private String counsel_date; // 게시글 작성일
	private String counsel_date_motify; // 게시글 수정일
	private String counsel_userId; // 게시글 작성한 유저아이디
	private String counsel_userId_motify; // 게시글 수정한 유저아이디
	private CounselReplyVO replyVO; // 댓글 테이블
	private boolean lockchk; // 비밀글 여부

	/*
	 * getter, setter
	 */

	public String getCounsel_title() {
		return counsel_title;
	}

	public boolean isLockchk() {
		return lockchk;
	}

	public void setLockchk(boolean lockchk) {
		this.lockchk = lockchk;
	}

	public void setCounsel_title(String counsel_title) {
		this.counsel_title = counsel_title;
	}

	public int getSeq_counsel() {
		return seq_counsel;
	}

	public void setSeq_counsel(int seq_counsel) {
		this.seq_counsel = seq_counsel;
	}

	public String getCounsel_content() {
		return counsel_content;
	}

	public void setCounsel_content(String counsel_content) {
		this.counsel_content = counsel_content;
	}

	public int getCounsel_reply() {
		return counsel_reply;
	}

	public void setCounsel_reply(int counsel_reply) {
		this.counsel_reply = counsel_reply;
	}

	public String getCounsel_date() {
		return counsel_date;
	}

	public void setCounsel_date(String counsel_date) {
		// 시간 뒤에 .ms 제거
		counsel_date = counsel_date.substring(0, counsel_date.lastIndexOf("."));
		this.counsel_date = counsel_date;

	}

	public CounselReplyVO getReplyVO() {
		return replyVO;
	}

	public void setReplyVO(CounselReplyVO replyVO) {
		this.replyVO = replyVO;
	}

	public String getCounsel_date_motify() {
		return counsel_date_motify;
	}

	public void setCounsel_date_motify(String counsel_date_motify) {
		// 시간 뒤에 .ms 제거
		counsel_date_motify = counsel_date_motify.substring(0, counsel_date_motify.lastIndexOf("."));
		this.counsel_date_motify = counsel_date_motify;
	}

	public String getCounsel_userId() {
		return counsel_userId;
	}

	public void setCounsel_userId(String counsel_userId) {
		this.counsel_userId = counsel_userId;
	}

	public String getCounsel_userId_motify() {
		return counsel_userId_motify;
	}

	public void setCounsel_userId_motify(String counsel_userId_motify) {

		this.counsel_userId_motify = counsel_userId_motify;
	}

}
