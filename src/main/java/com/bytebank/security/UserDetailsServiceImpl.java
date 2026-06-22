package com.bytebank.security;

<<<<<<< HEAD
import com.bytebank.model.User;
import com.bytebank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
=======
import com.bytebank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
>>>>>>> 093ee2d (ByteBank V2 project stucture)
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String systemId) throws UsernameNotFoundException {
        User user = userRepository.findById(systemId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with systemId: " + systemId));

        if ("LOCKED".equals(user.getStatus())) {
            throw new RuntimeException("Account is locked");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getSystemId(),
                user.getPasswordHash(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
=======

/** Loads a User by System ID for Spring Security authentication. */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String systemId) throws UsernameNotFoundException {
        // TODO: map com.bytebank.model.User -> org.springframework.security.core.userdetails.User
        throw new UsernameNotFoundException("Not yet implemented: " + systemId);
>>>>>>> 093ee2d (ByteBank V2 project stucture)
    }
}
