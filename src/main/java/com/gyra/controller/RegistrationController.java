package com.gyra.controller;

import com.gyra.model.user.UserDto;
import com.gyra.service.interfaces.RegistrationService;
import com.gyra.util.Mappings;
import com.gyra.util.ModelAttributes;
import com.gyra.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(path = Mappings.REGISTER)
public class RegistrationController {

    private final RegistrationService registrationService;
    private String errorMessage;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String formRegister(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute(ModelAttributes.USER, userDto);
        if (errorMessage != null) {
            model.addAttribute(ModelAttributes.ERROR_MESSAGE, errorMessage);
            errorMessage = null;
        }
        return ViewNames.REGISTER;
    }

    @PostMapping
    public String register(@ModelAttribute(ModelAttributes.USER) @Validated UserDto userDto) {
        log.info("Got a registration request: " + userDto);
        try {
            registrationService.register(userDto);
        } catch (IllegalArgumentException exception) {
            log.error("An error has occurred in the registration process: "
                    + exception.getMessage());
            errorMessage = exception.getMessage();
            return Mappings.REDIRECT_REGISTER;
        }
        return Mappings.REDIRECT_LOGIN;
    }
}
