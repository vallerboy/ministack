package pl.oskarpolak.ministack.model.service;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.oskarpolak.ministack.model.entity.UserEntity;
import pl.oskarpolak.ministack.model.form.LoginForm;
import pl.oskarpolak.ministack.model.repository.UserRepository;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldLoginForCorrectData() {
        //given
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail("test@email.pl");
        loginForm.setPassword("tajnehaslo");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(loginForm.getEmail());
        userEntity.setPassword(userService.getBCrypt().encode(loginForm.getPassword()));

        Optional<UserEntity> responseFromDatabase = Optional.of(userEntity);

        //when
        when(userRepository.findByEmail(loginForm.getEmail())).thenReturn(responseFromDatabase);

        //then
        Assertions.assertThat(userService.tryLogin(loginForm)).isTrue();
    }

//    @Test
//    public void shouldControllerLoginUser() throws Exception {
//        //given
//        String login = "login1";
//        String password = "password1";
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setEmail(login);
//        userEntity.setPassword(userService.getBCrypt().encode(password));
//
//        Optional<UserEntity> responseFromDatabase = Optional.of(userEntity);
//
//        //when
//        when(userRepository.findByEmail(login)).thenReturn(responseFromDatabase);
//
//        //then
//        mockMvc.perform(post("/user/login")
//                            .content("{\"login\": \"login1\", \"password\": \"password1\"}")
//                            .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(status().is3xxRedirection());
//
//    }
}