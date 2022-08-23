package back.jjowin.service;

import back.jjowin.domain.Project;
import back.jjowin.domain.ProjectSkill;
import back.jjowin.domain.ProjectStatus;
import back.jjowin.repository.ProjectRepository;
import back.jjowin.repository.ProjectSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectSkillService {
    private final ProjectSkillRepository projectSkillRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public void register(ProjectSkill[] projectSkills, Long projectId){

        Project project = projectRepository.findOne(projectId);

        for (int i=0; i<projectSkills.length;i++){
            projectSkills[i].setProject(project);
            projectSkillRepository.save(projectSkills[i]);
        }
    }

}
