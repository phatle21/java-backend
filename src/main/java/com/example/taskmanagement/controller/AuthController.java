package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.UserDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class AuthController {

    private final PasswordEncoder passwordEncoder;

    public AuthController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // Trang đăng nhập
    @GetMapping("/login")
    public String loginPage() {
        return "login";  // Trả về view login.html
    }

    // Trang đăng ký
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserDto());  // Gửi đối tượng UserDto cho form đăng ký
        return "register";  // Trả về view register.html
    }

    // Xử lý đăng ký người dùng
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "register";  // Nếu có lỗi thì trả về lại trang đăng ký
        }

        // Mã hóa mật khẩu và lưu người dùng (Ở đây bạn có thể lưu vào cơ sở dữ liệu)
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        // Thêm vào cơ sở dữ liệu hoặc bộ nhớ tạm thời

        return "redirect:/login";  // Sau khi đăng ký thành công, chuyển hướng đến trang login
    }
}
