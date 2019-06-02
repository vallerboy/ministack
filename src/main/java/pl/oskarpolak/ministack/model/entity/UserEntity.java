package pl.oskarpolak.ministack.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.oskarpolak.ministack.model.form.RegisterForm;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    public enum AccountType {
        ADMIN,
        MODERATOR,
        USER;
    }

    @Id
    @GeneratedValue
    private int id;
    @JsonIgnore
    private String email;
    private String nickname;
    @JsonIgnore
    private String password;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public UserEntity() { }

    public UserEntity(RegisterForm registerForm){
        this.email = registerForm.getEmail();
        this.nickname = registerForm.getNickname();
        this.password = registerForm.getPassword();
    }
}
