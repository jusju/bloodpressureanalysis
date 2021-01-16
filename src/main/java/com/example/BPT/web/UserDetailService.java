package com.example.BPT.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.BPT.domain.User;
import com.example.BPT.domain.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
	private final UserRepository repository;

	@Autowired
	public UserDetailService(UserRepository userRepository) {
		this.repository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User currentUser = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username,
				currentUser.getPasswordHash(), AuthorityUtils.createAuthorityList(currentUser.getRole()));
		System.out.println("ROLE: " + currentUser.getRole());
		return user;
	}
}
