package kr.lfin.exam.domains;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {
    private Long uId;
    private String email;
    private String password;
    private String name;
    private String phone;
}
