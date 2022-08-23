package back.jjowin.service;

import back.jjowin.domain.User;
import back.jjowin.domain.UserSkill;
import back.jjowin.repository.UserRepository;
import back.jjowin.repository.UserSkillRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserSkillService userSkillService;
    @Autowired
    UserSkillRepository userSkillRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager em;
}

