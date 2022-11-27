package com.ravid.clothes_marketplace.app.security;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ravid.clothes_marketplace.app.db.repo.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.ravid.clothes_marketplace.app.db.model.User> user = repo.findById(username);
        if (user.isPresent()) {
			return new User(user.get().getId(), user.get().getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}