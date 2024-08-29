package fr.org.aelion.atos2024.cyber.controllers.api;

import fr.org.aelion.atos2024.cyber.models.entities.Credentials;
import fr.org.aelion.atos2024.cyber.models.entities.User;
import fr.org.aelion.atos2024.cyber.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutController {
    // protected or default (package) => to allow better testing
    // @Autowired
    // AuthService authservice

    // private but accessible for testing with constructor
    private AuthService authService;

    @Autowired
    public void AuthController(AuthService authservice){
        this.authService = authservice;
    }

    // signup
    @PostMapping("/signup")
    public User signup(@RequestBody @Valid Credentials credentials){
        // Sign up logic (hash + salt + db storage)
        return this.authService.signup(credentials.toUser()).toPublicUser();
    }

    // sign in
    @PostMapping("/signin")
    public String signin(@RequestBody User user){
        // Sign in logic (hash + salt + db storage)
        return this.authService.signin(user);
    }
}
