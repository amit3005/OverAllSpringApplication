package com.springoverallApplication.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springoverallApplication.demo.dao.AppUserREPO;
import com.springoverallApplication.demo.model.AppUser;
import com.springoverallApplication.demo.model.CustomUserDetails;

@Service
public class UserDetailService implements UserDetailsService {
	
	@Autowired
	private AppUserREPO userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser tblUserPass = userRepo.findByAppUserEmailId(username);
		if(tblUserPass == null) {
			throw new UsernameNotFoundException("User is Not Found Kindly Register first.");
		}else {
			return new CustomUserDetails(tblUserPass);
		}
	}

}
