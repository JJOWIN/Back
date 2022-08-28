package back.jjowin.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SignupDTO {
    private String name;
    private String nickname;
    private String email;
    private String password;
    private String job;
    private int jobLevel;
    private String selfIntro;
    private List<UserSkillDTO> userSkills;
}
