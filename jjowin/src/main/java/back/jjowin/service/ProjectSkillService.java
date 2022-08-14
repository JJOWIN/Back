package back.jjowin.service;

import back.jjowin.domain.ProjectSkill;
import back.jjowin.repository.ProjectSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectSkillService {
    private final ProjectSkillRepository projectSkillRepository;

    @Transactional
    public Long register(ProjectSkill projectSkill){
        projectSkillRepository.save(projectSkill);
        return projectSkill.getId();
    }

}
