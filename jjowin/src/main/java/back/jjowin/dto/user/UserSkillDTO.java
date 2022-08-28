package back.jjowin.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserSkillDTO {
    private String name;

    public UserSkillDTO() {
    }

    public UserSkillDTO(String name) {
        this.name = name;
    }
}
