package fr.org.aelion.atos2024.cyber.services;

import fr.org.aelion.atos2024.cyber.models.entities.User;
import fr.org.aelion.atos2024.cyber.repositories.UserRepository;
import fr.org.aelion.atos2024.cyber.utils.HashUtils;
import fr.org.aelion.atos2024.cyber.utils.TokenUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final HashUtils hashUtils;
    private final UserRepository userRepository;

    public AuthService(HashUtils hashUtils, UserRepository userRepository) {
        this.hashUtils = hashUtils;
        this.userRepository = userRepository;
    }

    public User signup(User user) {
        user.setPwd(this.hashUtils.generate(user.getPwd()));
        // save in db
        return this.userRepository.save(user);
    }

    public String signin(User u) {
        // TODO: handle a custom exception
        return this.generateToken(
                this.userRepository.findByEmailAndPwd(u.getEmail(), this.hashUtils.generate(u.getPwd()))
                        .orElseThrow(RuntimeException::new)
        );
    }

    private String generateToken(User user) {
        // TODO: create a real JWT Token
        return TokenUtils.generateJWT(user);
    }
}
