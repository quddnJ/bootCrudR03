package com.bit;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.Member;
import com.bit.domain.Profile;
import com.bit.persistence.MemberRepository;
import com.bit.persistence.ProfileRepository;

import lombok.extern.java.Log;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Log
@Commit // 테스트 결과를 commit

public class ProfileTests {
	
	@Autowired
	MemberRepository mRepo;
	@Autowired
	ProfileRepository pRepo;
	
	@Test
	public void createTbl() {
		System.out.println("테이블 생성하기위해 런");
	}
	
	@Test
	public void testInsertMember() {
		IntStream.range(1, 101).forEach(i->{
			Member member = new Member();
			member.setUid("user"+i);
			member.setUpw("pw"+i);
			member.setUname("사용자"+i);
			mRepo.save(member);
		});
	}
	
	@Test
	public void testInsertProfile() {
		Member member = new Member();
		member.setUid("user1");
		for (int i = 1 ; i < 5; i++) {
			Profile profile = new Profile();
			profile.setFname("face"+i+".jpg");
			if(i==1)
				profile.setCurrent(true);
			profile.setMember(member);
			pRepo.save(profile);
		}
	}
	
	@Test
	public void testFetchJoin1() {
		mRepo.getMemberWithProfileCount("user1")
		.forEach(arr->System.out.println(Arrays.toString(arr)));
	}
	
	@Test
	public void testFetchJoin2() {
		mRepo.getMemberWithProfile("user1")
		.forEach(arr->System.out.println(Arrays.toString(arr)));
	}

}
