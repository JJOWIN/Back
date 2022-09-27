package back.jjowin.service;

import back.jjowin.domain.EmailToken;
import back.jjowin.domain.User;
import back.jjowin.dto.email.RequestEmailTokenDTO;
import back.jjowin.repository.EmailTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailTokenService {

    private final EmailSenderService emailSenderService;
    private final EmailTokenRepository emailTokenRepository;
    private final UserService userService;

    @Transactional
    public String createEmailToken(RequestEmailTokenDTO requestEmailTokenDTO){
        Assert.notNull(requestEmailTokenDTO.getUserId(), "userId가 유효하지 않습니다.");
        String local = "http://localhost:8080";
        String server = "http://43.200.200.255:8080";
        String confirmLink = local + "/confirm-mail?token=";
        // 토큰 생성
        EmailToken emailToken = EmailToken.createEmailToken(requestEmailTokenDTO.getUserId());
        emailTokenRepository.save(emailToken);

        // 이메일 전송
        User findUser = userService.getUserInfo(requestEmailTokenDTO.getUserId());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(findUser.getEmail());
        mailMessage.setSubject("JJOWIN 회원가입 이메일 인증");

        mailMessage.setText(confirmLink + emailToken.getId());
        emailSenderService.sendEmail(mailMessage);

        return emailToken.getId();
    }
    // 유효한 토큰 가져오기
    public EmailToken findByIdAndExpirationDateAfterAndExpired(String emailTokenId) {
        Optional<EmailToken> emailToken = emailTokenRepository
                .findByIdAndExpirationDateAfterAndIsExpired(emailTokenId, LocalDateTime.now(), false);

        // 토큰이 없다면 예외 발생
        return emailToken.orElseThrow(() -> new IllegalStateException("토큰 정보가 유효하지 않습니다."));
    }

    @Transactional
    public Boolean verifyEmail(String token) {
        EmailToken findEmailToken = findByIdAndExpirationDateAfterAndExpired(token);
        if (findEmailToken.getIsExpired()) {
            return false;
        }
        findEmailToken.setIsExpired(true);
        userService.setCertEmail(findEmailToken.getUserId());
        return true;
    }
}
