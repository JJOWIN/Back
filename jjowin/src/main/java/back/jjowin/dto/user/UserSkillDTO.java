package back.jjowin.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserSkillDTO {
    private String name;
    private int level;

    public UserSkillDTO() {
    }

    public UserSkillDTO(String name) {
        this.name = name;
    }

    public UserSkillDTO(String name, int level) {
        this.name = name;
        this.level = level;
    }
}
