package back.jjowin.service;

import back.jjowin.domain.User;
import back.jjowin.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입(){
        //given
        User user = new User();
        user.setEmail("abcd@abc.com");
        user.setPassword("1234");

        //when
        Long id = userService.signUp(user);

        //then
        assertEquals(user.getId(), id);
    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원예제(){
        //given
        User user1 = new User();
        user1.setEmail("abcd@abc.com");
        user1.setPassword("1234");

        User user2 = new User();
        user2.setEmail("abcd@abc.com");
        user2.setPassword("1234");

        //when
        userService.signUp(user1);
        userService.signUp(user2);

        //then
        fail("실패해야함");
    }
}