package back.jjowin.dto.project;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class ProjectCreateDTO {
    private String name;

    private String category;

    private Boolean isContest;

    private Long contestNo;

    private Long leader;

    private Boolean joinSchool;

    private String schoolName;

    private String description;

    private LocalDate endDate;

    private String image;

    private List<ProjectSkillDTO> projectSkills;

    private List<RecruitInfoDTO> recruitInfos;

}
