package com.bit.persistence;

import org.springframework.data.repository.CrudRepository;

import com.bit.domain.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long>{
	
	

}
