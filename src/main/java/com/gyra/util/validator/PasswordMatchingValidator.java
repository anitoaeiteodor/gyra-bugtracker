package com.gyra.util.validator;

import com.gyra.model.user.UserDto;
import org.springframework.stereotype.Component;

@Component
public class PasswordMatchingValidator {

    public boolean isValid(UserDto userDto) {
        return userDto.getPassword().equals(userDto.getMatchingPassword());
    }
}
