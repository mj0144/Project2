package com.mvc.counsel.user.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfo implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * user_info 테이블. 사용자 정보.
	 */
	private String id; // 사용자 아이디
	private String password; // 사용자 비밀번호
	private String join_date; // 가입일
	private String authority; // 권한

	public String getJoin_date() {
		return join_date;
	}

	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	/*
	 * UserDetails
	 */

	// 사용자가 가지고 있는 권한 목록들을 list형태로 반환
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(authority));
		return auth;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return id;
	}

	/*
	 * 계정의 만료여부(true : 만료안됨)
	 */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * 계정이 잠겨있는지 여부(true: 잠기지 않음)
	 */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * 비밀번호의 만료 여부(true: 만료안됨)
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * 계정 사용가능 여부(true: 사용가능)
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
