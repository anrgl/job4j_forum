package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

import java.util.Optional;

@Controller
public class LoginControl {
    private final UserService users;

    public LoginControl(UserService users) {
        this.users = users;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user) {
        Optional<User> u = users.findByUsername(user.getUsername());
        if (u.isPresent()) {
            if (u.get().getPassword().equals(user.getPassword())) {
                return "redirect:/";
            }
        }
        return "redirect:/login?error=true";
    }
}
