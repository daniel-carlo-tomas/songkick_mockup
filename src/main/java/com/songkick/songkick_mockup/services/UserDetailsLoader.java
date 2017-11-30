package com.songkick.songkick_mockup.services;


import com.songkick.songkick_mockup.models.User;
import com.songkick.songkick_mockup.models.UserWithRoles;
import com.songkick.songkick_mockup.repositories.Users;
import com.songkick.songkick_mockup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class UserDetailsLoader implements UserDetailsService {
    private final Users users;
    private UsersRepository usersRepository;

    @Autowired
    public UserDetailsLoader(Users users, UsersRepository usersRepository){
        this.users = users;
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        return new UserWithRoles(user, Collections.emptyList());
    }
}
