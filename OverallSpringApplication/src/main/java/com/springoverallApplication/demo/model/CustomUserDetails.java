package com.springoverallApplication.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends AppUser implements UserDetails {
	
	public CustomUserDetails(AppUser user) {
		super(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<Role> list = super.getRolesList();
		List<GrantedAuthority> listGrantedAuthority = new ArrayList<>();
		for(Role role : list) {
			listGrantedAuthority.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return listGrantedAuthority;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return super.getAppUserEmailId();
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getAppUserPassword();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
