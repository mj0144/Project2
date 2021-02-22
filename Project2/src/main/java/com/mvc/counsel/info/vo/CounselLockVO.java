package com.mvc.counsel.info.vo;

public class CounselLockVO {

	private int seq_counsel;	//비밀글인 게시글 번호
	private String lockPassword;	//비밀글 비밀번호

	public int getSeq_counsel() {
		return seq_counsel;
	}

	public void setSeq_counsel(int seq_counsel) {
		this.seq_counsel = seq_counsel;
	}

	public String getLockPassword() {
		return lockPassword;
	}

	public void setLockPassword(String lockPassword) {
		this.lockPassword = lockPassword;
	}

}
