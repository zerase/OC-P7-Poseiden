package com.nnk.springboot.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;


@Service
public class MyCustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User myUser  = userRepository.findByUsername(username);
		if(myUser == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		GrantedAuthority authority = new SimpleGrantedAuthority(myUser.getRole());
        UserDetails userDetails = (UserDetails)new org.springframework.security.core.userdetails.User(myUser.getUsername(),
                myUser.getPassword(), Arrays.asList(authority));
        return userDetails;
	}

}
