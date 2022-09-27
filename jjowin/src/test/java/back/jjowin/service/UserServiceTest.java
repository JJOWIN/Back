//package back.jjowin.service;
//
//import back.jjowin.domain.User;
//import back.jjowin.dto.user.LoginDTO;
//import back.jjowin.dto.user.SignupDTO;
//import back.jjowin.dto.user.UserSkillDTO;
//import back.jjowin.repository.UserRepository;
//import org.junit.Assert;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//class UserServiceTest {
//
//    @Autowired
//    UserService userService;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    EntityManager em;
//
//    @DisplayName("회원가입 성공")
//    @Test
//    void signUp() {
//        //Given(준비)
//        SignupDTO signupDTO = new SignupDTO();
//        signupDTO.setName("테스트1");
//        signupDTO.setNickname("닉네임1");
//        signupDTO.setEmail("abcd1234@abcd.com");
//        signupDTO.setPassword("abcd");
//        signupDTO.setJob("직무1");
//        signupDTO.setJobLevel(5);
//        signupDTO.setSelfIntro("자기소개1");
//        List<UserSkillDTO> userSkillDTOList = new ArrayList<>();
//        userSkillDTOList.add(new UserSkillDTO("스킬1"));
//        userSkillDTOList.add(new UserSkillDTO("스킬2"));
//        signupDTO.setUserSkills(userSkillDTOList);
//        //When(실행)
//        User user = userService.signUp(signupDTO);
//        //Then(검증)
//        assertEquals(user.getName(), signupDTO.getName());
//        assertEquals(user.getNickname(), signupDTO.getNickname());
//        assertEquals(user.getJob(), signupDTO.getJob());
//        assertEquals(user.getJobLevel(), signupDTO.getJobLevel());
//        assertEquals(user.getSelfIntro(), signupDTO.getSelfIntro());
//        assertEquals(user.getEmail(), signupDTO.getEmail());
//        assertEquals(user.getUserSkills().size(), signupDTO.getUserSkills().size());
//
//    }
//
//    User signupForTest(){
//        SignupDTO signupDTO = new SignupDTO();
//        signupDTO.setName("테스트1");
//        signupDTO.setNickname("닉네임1");
//        signupDTO.setEmail("test@abcd.com");
//        signupDTO.setPassword("abcd");
//        signupDTO.setJob("직무1");
//        signupDTO.setJobLevel(5);
//        signupDTO.setSelfIntro("자기소개1");
//        List<UserSkillDTO> userSkillDTOList = new ArrayList<>();
//        userSkillDTOList.add(new UserSkillDTO("스킬1"));
//        userSkillDTOList.add(new UserSkillDTO("스킬2"));
//        signupDTO.setUserSkills(userSkillDTOList);
//
//        return userService.signUp(signupDTO);
//    }
//    @DisplayName("로그인 성공")
//    @Test
//    void login() {
//        //Given(준비)
//        User user = signupForTest();
//        LoginDTO loginDTO = new LoginDTO();
//        loginDTO.setEmail("test@abcd.com");
//        loginDTO.setPassword("abcd");
//
//        //When(실행)
//        User result = userService.login(loginDTO);
//
//        //Then(검증)
//        assertEquals(user, result);
//    }
//
//    @DisplayName("닉네임 중복 발생")
//    @Test
//    void validateDuplicateNickname() {
//        //Given(준비)
//        User user = signupForTest();
//        String nickName = "닉네임1";
//
//        //When(실행)
//        boolean result = userService.isDuplicateNickname(nickName);
//
//        //Then(검증)
//        assertEquals(true, result);
//
//    }
//
//    @Test
//    void getUserInfo() {
//        //Given(준비)
//        User user = signupForTest();
//        //When(실행)
//        User findUser = userService.getUserInfo(user.getId());
//        //Then(검증)
//        assertEquals(user, findUser);
//    }
//
//}