package com.bit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.PDSBoard;
import com.bit.domain.PDSFile;
import com.bit.persistence.PDSBoardRepository;

import lombok.extern.java.Log;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Log
@Commit // 테스트 결과를 commit

public class PDSBoardTests {
	
	@Autowired
	private PDSBoardRepository pRepo;
	
	@Test
	public void testCreate() {
		System.out.println("테이블 생성");
	}
	
	@Test
	public void testInsertPDS() {
		PDSBoard pds = new PDSBoard();
		pds.setPname("DOCUMENT 1-2");
		pds.setPwriter("admin");
		PDSFile file1 = new PDSFile();
		file1.setPdsfile("file1.doc");
		PDSFile file2 = new PDSFile();
		file2.setPdsfile("file2.doc");
		// asList 가변인수 메소드
		pds.setFiles(Arrays.asList(file1, file2));
		log.info("try to saved pds");
		pRepo.save(pds);
	}
	
	@Transactional //트랜잭션
	@Test
	public void testUpdateFileName() {
		Long fno = 1L;
		String newName = "updateFile2.doc";
		int count = pRepo.updatePDSFile(fno, newName);
		log.info("**************update count : " + count);
	}
	
	@Transactional
	@Test
	public void testDeletePDSFile() {
		Long fno = 2L;
		int count = pRepo.deletePDSFile(fno);
		log.info("***************delete count : " + count);
	}
	
	@Test
	public void testInsertDummies() {
		List<PDSBoard> list = new ArrayList<PDSBoard>();
		IntStream.range(1, 100).forEach(i->{
			PDSBoard pds = new PDSBoard();
			pds.setPname("자료 " + i);
			PDSFile file1 = new PDSFile();
			file1.setPdsfile("file1.doc");
			PDSFile file2 = new PDSFile();
			file2.setPdsfile("file2.doc");
			pds.setFiles(Arrays.asList(file1, file2));
			log.info("try to savs pds :" + i);
			list.add(pds);
		});
		pRepo.saveAll(list);
	}
	
	@Test
	public void testViewSummary() {
		pRepo.getSummary().forEach(arr->log.info(Arrays.toString(arr)));
	}

}
