package kr.lfin.exam.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String phone;

    public static User createUser(String email, String password, String name, String phone){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setPhone(phone);
        return user;
    }
}
