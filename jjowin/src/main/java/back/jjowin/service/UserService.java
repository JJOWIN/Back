package back.jjowin.service;

import back.jjowin.domain.User;
import back.jjowin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long signUp(User user){
        validateDuplicateUser(user);
        user.setPassword(getEncryptPassword(user.getPassword()));
        userRepository.save(user);
        return user.getId();
    }

    /**
     * 비밀번호 암호화
     * @param password
     * @return  암호화된 비밀번호
     */
    private String getEncryptPassword(String password){
        MessageDigest md = null;
        String result = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] encryptedPassword = md.digest();
            StringBuilder sb = new StringBuilder();
            for(byte b: encryptedPassword){
                sb.append(String.format("%02x", b));
            }
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if(result == null){
            throw new IllegalStateException("비밀번호 암호화 오류입니다.");
        }
        return result;
    }
    private void validateDuplicateUser(User user){
        List<User> findUsers = userRepository.findByEmail(user.getEmail());
        if(!findUsers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }
}
