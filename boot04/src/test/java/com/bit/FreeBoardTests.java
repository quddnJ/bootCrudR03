package com.bit;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.FreeBoard;
import com.bit.domain.FreeBoardReply;
import com.bit.persistence.FreeBoardReplyRepository;
import com.bit.persistence.FreeBoardRepository;

import lombok.extern.java.Log;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Log
@Commit // 테스트 결과를 commit
public class FreeBoardTests {
	
	@Autowired
	private FreeBoardRepository bRepo;
	
	@Autowired
	private FreeBoardReplyRepository rRepo;
	
	@Test
	public void testCreate() {
		log.info("테이블 생성");
	}
	
	@Test
	public void insertDummy() {
		IntStream.range(1, 200).forEach(i->{
			FreeBoard board = new FreeBoard();
			board.setTitle("Free Board..."+i);
			board.setContent("Free Content..."+i);
			board.setWriter("user "+i%10);
			bRepo.save(board);
		});
	}
	
	//트랜잭션 없이 하기
	@Test
	public void insertReply1Way() {
		FreeBoard board = new FreeBoard();
		board.setBno(198L);
		FreeBoardReply reply = new FreeBoardReply();
		reply.setReply("REPLY......");
		reply.setReplyer("reply00");
		reply.setBoard(board);
		rRepo.save(reply);
	}
	
	@Transactional
	@Test
	public void insertReply2Way() {
		Optional<FreeBoard> result = bRepo.findById(199L);
		result.ifPresent(board->{
			List<FreeBoardReply> replies = board.getReplies();
			FreeBoardReply reply = new FreeBoardReply();
			reply.setReply("REPLY......");
			reply.setReplyer("reply00");
			reply.setBoard(board);
			replies.add(reply);
			board.setReplies(replies);
			bRepo.save(board);
		});
	}
	
	@Test
	public void testList() {
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		bRepo.getPage(paging).forEach(arr->{
			System.out.println("***"+Arrays.toString(arr));
		});
	}

}
