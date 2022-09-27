package back.jjowin.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginResultDTO {
    private Boolean isSchool;
    private String schoolName;
    private Boolean isCertMail;
    private Boolean isCertPhone;
}
