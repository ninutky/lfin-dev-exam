package kr.lfin.exam.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static kr.lfin.exam.SHA256.hashString;

@Entity
//@Table(name = "user")
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

//    public String SHA256(String password){
//        return hashString(password);
//    }
}
