package com.wbl.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wbl.dao.UserDao;
import com.wbl.models.UserInfo;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userDao.findByUsername(username);
		
		try {
			if(userInfo != null) {
				String uname = userInfo.getUsername();
				String pword = userInfo.getPassword();
				return new User(uname, pword, new ArrayList<>());
			}
		}catch(Exception e) {
			throw new UsernameNotFoundException("Incorrect username :"+username);
		}
		return null;
	}
	
	public UserInfo addUser(UserInfo user) {
		return userDao.save(user);
	}
}