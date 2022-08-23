package back.jjowin.DTO;

import back.jjowin.domain.Project;
import back.jjowin.domain.ProjectSkill;
import back.jjowin.domain.RecruitInfo;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProjectCreateDTO {
    private Project project;
    private ProjectSkill[] projectSkill;
    private RecruitInfo[] recruitInfo;
}
