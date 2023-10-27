package kr.lfin.exam.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
//@Table(name = "user")
@Getter
@Setter
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uId;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String phone;
    private Boolean deleted;

    public static User createUser(String email, String password, String name, String phone) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(bcryptHashPassword(password));
        user.setName(name);
        user.setPhone(phone);
        user.setDeleted(false);
        return user;
    }

    public static String bcryptHashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
