package dannydwicahyono.spring_commerce.service;

import dannydwicahyono.spring_commerce.dto.UserRegistrationDTO;
import dannydwicahyono.spring_commerce.entity.User;
import dannydwicahyono.spring_commerce.repository.UserRepository;
import dannydwicahyono.spring_commerce.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public void register(UserRegistrationDTO registrationDTO) {
        if (userRepository.existsByUsername(registrationDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .username(registrationDTO.getUsername())
                .password(BCrypt.hashpw(registrationDTO.getPassword(), BCrypt.gensalt()))
                .email(registrationDTO.getEmail())
                .role(registrationDTO.getRole())
                .build();

        userRepository.save(user);
    }
}
