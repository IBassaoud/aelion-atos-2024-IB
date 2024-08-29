package fr.org.aelion.atos2024.cyber.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class allows to validate credentials
 * and convert them to a basic user object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {
    @Column(unique = true)
    @Size(min = 6)
    @Email
    private String email;
    @Column(name="password")
    @Pattern(regexp="(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z-0-9]{8,}", message = "Minimum eight characters, at least one letter and one number")
    private String pwd;

    // TODO: we can also decide to handle the hashing logic here while converting to user
    public User toUser() {
        return new User(null, this.email, this.pwd);
    }
}
