package kr.lfin.exam.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.codec.digest.DigestUtils;

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
        user.setPassword(SHA256(password)); // 암호를 해시로 변환
        user.setName(name);
        user.setPhone(phone);
        return user;
    }

    public static String SHA256(String password){
        return DigestUtils.sha256Hex(password);
    }

}
