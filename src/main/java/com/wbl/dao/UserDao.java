package com.wbl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wbl.models.UserInfo;

@Repository
public interface UserDao extends  JpaRepository<UserInfo, Integer> {
  
	public UserInfo findByUsername(String username);

	
}
