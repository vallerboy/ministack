package pl.oskarpolak.ministack.model.form;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterForm {
    @Pattern(regexp = ".{5,30}", message = "asdasdasdasdasdasd" )
    private String nickname;
    @Pattern(regexp = ".+@.+\\..{2,5}")
    private String email;
    @Size(min = 6, max = 30)
    private String password;
}
