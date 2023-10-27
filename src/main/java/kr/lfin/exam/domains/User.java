package kr.lfin.exam.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
//@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uId;
    @Column(unique = true)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String name;
    private String phone;
    private Boolean deleted = false;

    public static User createUser(String email, String password, String name, String phone) {
        return User.builder()
                .email(email)
                .password(bcryptHashPassword(password))
                .name(name)
                .phone(phone)
                .deleted(false)
                .build();
    }

    public static String bcryptHashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
