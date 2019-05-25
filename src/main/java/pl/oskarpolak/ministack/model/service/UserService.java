package pl.oskarpolak.ministack.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.oskarpolak.ministack.model.entity.UserEntity;
import pl.oskarpolak.ministack.model.form.RegisterForm;
import pl.oskarpolak.ministack.model.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional( )
    public boolean registerUser(RegisterForm registerForm){
        //sprawdzanie po emialu czy jest zajety (nie bedzie dzialac bez transakcji)
        if(userRepository.existsByEmail(registerForm.getEmail())){
            return false;
        }

        UserEntity newUser = new UserEntity(registerForm);
        userRepository.save(newUser);
        return true;
    }
}
