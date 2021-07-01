package com.gyra.service;

import com.gyra.model.user.User;
import com.gyra.repository.UserRepository;
import com.gyra.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final static String EMAIL_TAKEN = "Email %s is already in use";

    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User)principal;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void updateUser(Long id, User user) {
        User oldUser = userRepository.getById(id);
        oldUser.setName(user.getName());
        oldUser.setSurname(user.getSurname());
        oldUser.setEmail(user.getEmail());
        log.info("Updating user with id " + id);
        log.info(oldUser.getEmail());
        userRepository.save(oldUser);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, email)));
    }

    @Transactional
    public void signUpUser(User user) throws IllegalArgumentException {
        if (isEmailInUse(user.getEmail())) {
            throw new IllegalArgumentException(String.format(EMAIL_TAKEN, user.getEmail()));
        }
        userRepository.save(user);
    }

    private boolean isEmailInUse(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
