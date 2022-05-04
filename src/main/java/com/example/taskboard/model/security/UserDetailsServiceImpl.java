package com.example.taskboard.model.security;

import com.example.taskboard.entity.users.Users;
import com.example.taskboard.entity.users.mapper.UsersSecurityMapper;
import com.example.taskboard.model.dataexeptions.UserNotFoundException;
import com.example.taskboard.repo.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final UsersSecurityMapper usersSecurityMapper;

    public UserDetailsServiceImpl(UsersRepository usersRepository, UsersSecurityMapper usersSecurityMapper) {
        this.usersRepository = usersRepository;
        this.usersSecurityMapper = usersSecurityMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String usersLogin) throws UsernameNotFoundException {
        Optional<Users> user = usersRepository.findUsersByUserLogin(usersLogin);

        return usersSecurityMapper.usersToUserDetails(user.orElseThrow(() -> new UserNotFoundException(usersLogin)));
    }
}
