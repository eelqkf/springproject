package com.project.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.repository.ManagerRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private ManagerRepository managerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.project.model.Manager> dbuser = managerRepository.findById(username);
		if (dbuser.isEmpty()) {
			throw new UsernameNotFoundException("Invalid username");
		}
		List<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority("QUERY"));
		auths.add(new SimpleGrantedAuthority("WRITE"));
		
		UserDetails ud = User
				.withUsername(dbuser.get().getManagerId())
				.password(dbuser.get().getManagerPassword())
				.authorities(auths)
				.roles("admin")
				.build();
		return ud;
	}
}
