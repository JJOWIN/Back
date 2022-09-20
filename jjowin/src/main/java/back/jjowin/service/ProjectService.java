package back.jjowin.service;

import back.jjowin.domain.*;
import back.jjowin.dto.project.ProjectCreateDTO;
import back.jjowin.dto.project.ProjectSkillDTO;
import back.jjowin.dto.project.RecruitInfoDTO;
import back.jjowin.dto.user.UserSkillDTO;
import back.jjowin.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectSkillRepository projectSkillRepository;
    private final RecruitInfoRepository recruitInfoRepository;
    private final UserRepository userRepository;
    private final ContestRepository contestRepository;

    @Transactional(readOnly = false)
    public void register(ProjectCreateDTO projectCreateDTO){
        Project project = new Project();
        //프로젝트 정보 저장
        project.setName(projectCreateDTO.getName());
        project.setCategory(projectCreateDTO.getCategory());
        project.setIsContest(projectCreateDTO.getIsContest());
        project.setContestNo(contestRepository.findOne(projectCreateDTO.getContestNo()));
        project.setLeader(userRepository.findOne(projectCreateDTO.getLeader()));
        project.setSchoolName(projectCreateDTO.getSchoolName());
        project.setDescription(projectCreateDTO.getDescription());
        project.setEndDate(projectCreateDTO.getEndDate());
        project.setImage(projectCreateDTO.getImage());
        project.setStatus(ProjectStatus.INSPECTION);
        projectRepository.save(project);

        //프로젝트 스킬 저장
        for (ProjectSkillDTO projectSkillDTO : projectCreateDTO.getProjectSkills()) {
            ProjectSkill projectSkill = new ProjectSkill();
            projectSkill.setProject(project);
            projectSkill.setName(projectSkillDTO.getName());
            projectSkillRepository.save(projectSkill);
        }

        //모집 세부 직무 저장
        for (RecruitInfoDTO recruitInfoDTO : projectCreateDTO.getRecruitInfos()) {
            RecruitInfo recruitInfo = new RecruitInfo();
            recruitInfo.setProject(project);
            recruitInfo.setJob(recruitInfoDTO.getJob());
            recruitInfo.setCount(recruitInfoDTO.getCount());
            recruitInfoRepository.save(recruitInfo);
        }
    }

}
