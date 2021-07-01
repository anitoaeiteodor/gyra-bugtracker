package com.gyra.service;

import com.gyra.model.user.User;
import com.gyra.model.user.UserDto;
import com.gyra.model.user.UserRole;
import com.gyra.security.PasswordEncoder;
import com.gyra.service.interfaces.RegistrationService;
import com.gyra.util.validator.EmailValidator;
import com.gyra.util.validator.PasswordMatchingValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserServiceImpl userServiceImpl;
    private final EmailValidator emailValidator;
    private final PasswordMatchingValidator passwordMatchingValidator;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(UserDto userDto) throws IllegalArgumentException {
        boolean isValidEmail = emailValidator.isValid(userDto.getEmail());
        boolean isValidPassword = passwordMatchingValidator.isValid(userDto);
        if (!isValidEmail) {
            throw new IllegalArgumentException("Email is not valid");
        }
        if (!isValidPassword) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        userServiceImpl.signUpUser(
                new User(
                        userDto.getFirstName(),
                        userDto.getLastName(),
                        userDto.getEmail(),
                        passwordEncoder.bCryptPasswordEncoder().encode(userDto.getPassword()),
                        UserRole.DEVELOPER
                )
        );

    }
}
