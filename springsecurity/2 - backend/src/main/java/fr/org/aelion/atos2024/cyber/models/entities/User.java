package fr.org.aelion.atos2024.cyber.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // /!\ Integer vs Long
    @Column(unique = true)
    @Size(min = 6)
    @Email
    // @Encrypt() // TODO: store as ecnrypted
    private String email;
    @Column(name="password")
    @NotNull
    @Size(min = 16) // TODO: check and validate min hash size value
    // password pattern is validated with credentials class (here ony HASH is stored and cant be validated the same way)
    // @Pattern(regexp="(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z-0-9]{8,}", message = "Minimum eight characters, at least one letter and one number") // Minimum eight characters, at least one letter and one number
    private String pwd; // TODO: is its possible check and validate hash with @Pattern?

    /**
     * Clean sensitive value for public usage
     * TODO: implement as DTO or other object mapping lib
     * @return current user without sensitive data
     */
    public User toPublicUser() {
        // TODO: clone() object?
        this.setPwd(null); // pwd will appear on serialization but empty
        return this;
    }
}
