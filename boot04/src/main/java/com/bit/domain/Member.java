package com.bit.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@ToString
@Entity @Table(name="tbl_members")
//동일한 내용인지 같은 객체인지 비교하는 메소드 자동생성
@EqualsAndHashCode(of="uid")

public class Member {
	
	@Id
	private String uid;
	private String upw;
	private String uname;

}
