package kr.lfin.exam.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean deleted;
}
