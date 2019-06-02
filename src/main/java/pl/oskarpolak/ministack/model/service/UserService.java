package pl.oskarpolak.ministack.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.oskarpolak.ministack.model.entity.UserEntity;
import pl.oskarpolak.ministack.model.form.LoginForm;
import pl.oskarpolak.ministack.model.form.RegisterForm;
import pl.oskarpolak.ministack.model.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
//@Scope(scopeName = "singleton")
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionService sessionService;

    @Transactional
    public boolean registerUser(RegisterForm registerForm){
        //sprawdzanie po emialu czy jest zajety (nie bedzie dzialac bez transakcji)
        if(userRepository.existsByEmail(registerForm.getEmail())){
            return false;
        }

        String passwordHash = getBCrypt().encode(registerForm.getPassword());
        registerForm.setPassword(passwordHash);

        UserEntity newUser = new UserEntity(registerForm);
        userRepository.save(newUser);
        return true;
    }

    public boolean tryLogin(LoginForm loginForm) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(loginForm.getEmail());
        if(!userOptional.isPresent()){
            return false;
        }
        boolean passwordMatches = getBCrypt().matches(loginForm.getPassword(), userOptional.get().getPassword());
        if(passwordMatches){
            sessionService.setLogin(true);
            sessionService.setNickname(userOptional.get().getNickname());
            sessionService.setUserId(userOptional.get().getId());
            sessionService.setAccountType(userOptional.get().getAccountType());
        }
        return passwordMatches;
    }

    public boolean isUserExits(int id){
        return userRepository.existsById(id);
    }

    @Bean
    public BCryptPasswordEncoder getBCrypt(){
        return new BCryptPasswordEncoder();
    }

}
