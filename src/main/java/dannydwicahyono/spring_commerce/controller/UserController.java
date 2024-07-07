package dannydwicahyono.spring_commerce.controller;

import dannydwicahyono.spring_commerce.dto.UserRegistrationDTO;
import dannydwicahyono.spring_commerce.dto.ApiResponse;
import dannydwicahyono.spring_commerce.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping(path = "/api/users/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<String> register(@RequestBody UserRegistrationDTO registrationDTO) {
        userService.register(registrationDTO);
        return ApiResponse.<String>builder()
                .data("User registration success")
                .build();
    }
}
