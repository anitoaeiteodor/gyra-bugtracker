package com.gyra.controller;

import com.gyra.model.user.User;
import com.gyra.repository.UserRepository;
import com.gyra.service.interfaces.UserService;
import com.gyra.util.Mappings;
import com.gyra.util.ModelAttributes;
import com.gyra.util.ViewNames;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping(Mappings.USER)
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String showProfile(Model model) {
        model.addAttribute(ModelAttributes.USERNAME, userService.getCurrentUser().getFullName());
        model.addAttribute(ModelAttributes.USER, userService.getCurrentUser());
        return ViewNames.USER;
    }

    @PostMapping
    public String updateProfile(@ModelAttribute(ModelAttributes.USER) User user,
                                @RequestParam(name = "id") Long id) {
        log.info("Update profile query: " + user.toString());
        userService.updateUser(id, user);
        return Mappings.REDIRECT_DASH;
    }
}
