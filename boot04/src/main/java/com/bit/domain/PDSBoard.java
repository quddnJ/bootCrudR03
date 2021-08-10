package com.bit.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@ToString(exclude = "files")
@Entity @Table(name="tbl_pds")

public class PDSBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	private String pname;
	private String pwriter;
	
	@OneToMany(cascade = CascadeType.ALL) //수정 삭제 허용
	@JoinColumn(name = "pdsno")
	private List<PDSFile> files;

}
