package back.jjowin.service;

import back.jjowin.domain.User;
import back.jjowin.domain.UserSkill;
import back.jjowin.dto.LoginDTO;
import back.jjowin.dto.UserSkillDTO;
import back.jjowin.dto.SignupDTO;
import back.jjowin.repository.UserRepository;
import back.jjowin.repository.UserSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserSkillRepository userSkillRepository;
    /**
     * 회원 가입
     *
     * @param signupDTO
     * @return
     */
    @Transactional(readOnly = false)
    public void signUp(SignupDTO signupDTO) {
        User user = new User();
        if (signupDTO.getName().isBlank()) {
            throw new IllegalStateException("이름이 유효하지 않습니다.");
        }
        if (signupDTO.getNickname().isBlank()) {
            throw new IllegalStateException("닉네임이 유효하지 않습니다.");
        }
        if (!isValidEmailAddress(signupDTO.getEmail())) {
            throw new IllegalStateException("이메일 형식이 유효하지 않습니다.");
        }
        validateDuplicateUser(signupDTO.getEmail());
        if (signupDTO.getJobLevel() < 1 || signupDTO.getJobLevel() > 5) {
            throw new IllegalStateException("직무 숙련도가 유효하지 않습니다.");
        }
        if (signupDTO.getJob().isBlank()) {
            throw new IllegalStateException("직무 이름이 유효하지 않습니다.");
        }
        if (signupDTO.getUserSkills().isEmpty()) {
            throw new IllegalStateException("스킬을 입력해주세요");
        }
        // 유저 정보 저장
        user.setName(signupDTO.getName());
        user.setNickname(signupDTO.getNickname());
        user.setEmail(signupDTO.getEmail());
        user.setPassword(getEncryptPassword(signupDTO.getPassword()));
        user.setJob(signupDTO.getJob());
        user.setJobLevel(signupDTO.getJobLevel());
        user.setSelfIntro(signupDTO.getSelfIntro());
        userRepository.save(user);
        // 유저 스킬 저장
        for (UserSkillDTO userSkillDTO : signupDTO.getUserSkills()) {
            UserSkill userSkill = new UserSkill();
            userSkill.setUser(user);
            userSkill.setName(userSkillDTO.getName());
            userSkillRepository.save(userSkill);
        }

    }

    /**
     * 비밀번호 암호화
     *
     * @param password
     * @return 암호화된 비밀번호
     */
    private String getEncryptPassword(String password) {
        MessageDigest md = null;
        String result = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] encryptedPassword = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : encryptedPassword) {
                sb.append(String.format("%02x", b));
            }
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (result == null) {
            throw new IllegalStateException("비밀번호 암호화 오류입니다.");
        }
        return result;
    }

    /**
     * 이메일 중복검사
     *
     * @param email
     */
    private void validateDuplicateUser(String email) {
        List<User> findUsers = userRepository.findByEmail(email);
        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    /**
     * 이메일 형식 검사
     *
     * @param email
     * @return
     */
    public static boolean isValidEmailAddress(String email) {
        boolean result = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            result = true;
        }
        return result;
    }

    @Transactional
    public User login(LoginDTO loginDTO) {
        List<User> findUsers = userRepository.findByEmail(loginDTO.getEmail());
        if (findUsers.size() != 1) {
            throw new IllegalStateException("유효하지 않은 이메일입니다.");
        }
        String inputPassword = getEncryptPassword(loginDTO.getPassword());
        String findPassword = findUsers.get(0).getPassword();
        if (!inputPassword.equals(findPassword)) {
            throw new IllegalStateException("비밀번호가 유효하지 않습니다.");
        }

        return findUsers.get(0);
    }

    public void validateDuplicateNickname(String nickname) {
        List<User> findUsers = userRepository.findByNickname(nickname);
        if (findUsers.size() != 0) {
            throw new IllegalStateException("닉네임이 중복됩니다.");
        }
    }

    public User getUserInfo(Long id) {
        User findUser = userRepository.findOne(id);
        if (findUser == null) {
            throw new IllegalStateException("id를 가진 유저가 존재하지 않습니다.");
        }
        return findUser;
    }

}
