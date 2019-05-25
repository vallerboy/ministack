package pl.oskarpolak.ministack.model.form;

import lombok.Data;

@Data
public class RegisterForm {
    private String nickname;
    private String email;
    private String password;
}
