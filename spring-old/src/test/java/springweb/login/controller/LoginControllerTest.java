package springweb.login.controller;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import springweb.login.domain.Role;
import springweb.login.domain.User;
import springweb.login.domain.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    public void createUser(){

        User user = User.builder()
                .email("hello@world.com")
                .pw("helloworld")
                .role(Role.GUEST)
                .build();

        userRepository.save(user);
    }

    @AfterEach
    public void deleteUser(){
        userRepository.deleteAll();
    }

    @Test
    void 로그인테스트() throws Exception {

        //given
        String uri = "/login?email=hello@world.com&pw=helloworld";

        //when
        ResponseEntity<String> response = testRestTemplate.getForEntity(uri, String.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("true");

    }

    @Test
    void 권한성공테스트() {
        //given
        loginForTest();
        String uri = "/test/guest";

        //when
        ResponseEntity<String> response = testRestTemplate.getForEntity(uri, String.class);
        RestTemplate restTemplate = testRestTemplate.getRestTemplate();


        //then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void 권한실패테스트() {
        //given
        loginForTest();
        String uri = "/test/user";

        //when
        ResponseEntity<String> response = testRestTemplate.getForEntity(uri, String.class);
        RestTemplate restTemplate = testRestTemplate.getRestTemplate();


        //then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    private void loginForTest(){
        String uri = "/login?email=hello@world.com&pw=helloworld";
        ResponseEntity<String> forEntity = testRestTemplate.getForEntity(uri, String.class);
    }



}