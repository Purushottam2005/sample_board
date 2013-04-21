package com.jkonury.www.user.model;

import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.jkonury.www.common.model.Common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Common{
	private int userSeq;
	
	@Size(min=4, max=12, message="아이디는 4자 이상 12자 이하")
	private String id;
	
	@Size(min=4, max=12, message="비밀번호는 4자 이상 12자 이하")
	private String passwd;
	
	
	private String passwdCheck;
	
	@Size(min=8, max=30, message="이메일은 필수 입력입니다")
	private String email;
	
	@Size(min=2, max=20, message="이름은 필수 입력입니다")
	private String name;
	
	@Size(min=1, message="전화번호는 필수 입력입니다")
	private String tel;
	
	@Size(min=1, message="핸드폰 번호는 필수 입력입니다")
	private String phone;
	private String role;
	private String modifiedDate;
	private String zipCode;
	private String dftAddr;
	private String dtlAddr;
	private int loginCount;
	
	public User() {
	}
	
	public User(int userSeq, String id, String passwd, 
			String email, String name, String tel, String phone, String role,
			String modifiedDate, String zipCode, String dftAddr,
			String dtlAddr, int loginCount) {
		super();
		this.userSeq = userSeq;
		this.id = id;
		this.passwd = passwd;
		this.email = email;
		this.name = name;
		this.tel = tel;
		this.phone = phone;
		this.role = role;
		this.modifiedDate = modifiedDate;
		this.zipCode = zipCode;
		this.dftAddr = dftAddr;
		this.dtlAddr = dtlAddr;
		this.loginCount = loginCount;
	}
	
	public void login() {
		this.loginCount++;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
		
}
