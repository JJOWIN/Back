package back.jjowin.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class EmailToken {

    private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L;    // 이메일 토큰 만료 시간

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    private Long userId;

    private Boolean isExpired;

    private LocalDateTime expirationDate;

    // 이메일 인증 토큰 생성
    public static EmailToken createEmailToken(Long userId) {
        EmailToken emailToken = new EmailToken();
        emailToken.expirationDate = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE); // 5분 후 만료
        emailToken.userId = userId;
        emailToken.isExpired = false;
        return emailToken;
    }

    // 토큰 만료
    public void setTokenToUsed() {
        this.isExpired = true;
    }
}
