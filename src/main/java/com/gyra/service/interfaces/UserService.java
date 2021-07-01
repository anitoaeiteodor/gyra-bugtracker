package com.gyra.service.interfaces;

import com.gyra.model.user.User;

import java.util.List;

public interface UserService {
    User getCurrentUser();
    List<User> getAllUsers();
    void updateUser(Long id, User user);
}
