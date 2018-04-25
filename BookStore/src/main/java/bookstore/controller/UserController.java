package bookstore.controller;

import bookstore.dto.UserDto;
import bookstore.entity.User;
import bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/findall")
    public String findAllUsers(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "user-list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "user-form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createUser(Model model, @ModelAttribute("user") @Valid UserDto userDto, BindingResult result) {
        if (!result.hasErrors()) {
            userService.create(userDto);
            return "redirect:/admin";
        } else {
            model.addAttribute("messages", ErrorExtractor.constructErrors(result));
            return "user-form";
        }
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public String delete(@PathVariable(value="userId") Integer id) {
        userService.delete(id);
        return "redirect:/user/findall";
    }

    @RequestMapping(value="/update/{userId}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable(value="userId") Integer id) {
        User user = userService.findById(id);
        UserDto userDto = new UserDto(user.getUsername(), user.getPassword(), user.getRole());
        userDto.setId(id);
        model.addAttribute("user", userDto);
        return "user-update-form";
    }

    @RequestMapping(value="/update/{userId}", method = RequestMethod.POST)
    public String update(Model model, @ModelAttribute("user") @Valid UserDto userDto, BindingResult result, @PathVariable(value="userId") Integer id) {
        if (!result.hasErrors()) {
            String encodedPassword = new ShaPasswordEncoder().encodePassword(userDto.password, "SHA-1");
            userDto.setPassword(encodedPassword);
            userService.update(id, userDto);
            return "redirect:/user/findall";
        } else {
            model.addAttribute("messages", ErrorExtractor.constructErrors(result));
            UserDto newUserDto = new UserDto();
            userDto.setId(id);
            model.addAttribute(newUserDto);
            return "user-update-form";
        }
    }
}
