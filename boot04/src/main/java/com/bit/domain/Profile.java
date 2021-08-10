package com.bit.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
//member는 참조라 ToString으로 제외시킴
@ToString(exclude = "member")
@Entity @Table(name="tbl_profile")
//동일한 내용인지 같은 객체인지 비교하는 메소드 자동생성
@EqualsAndHashCode(of="fname")

public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fno;
	private String fname;
	private boolean current;
	
	//연관관계
	//fk로 자동으로 추가되고 인덱스(색인)추가됨
	@ManyToOne
	private Member member;

}
