package back.jjowin.repository;


import back.jjowin.domain.ProjectSkill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectSkillRepository {

    private final EntityManager em;

    /**
     * 프로젝트 스킬 저장 (Insert & Update)
     */
    public void save(ProjectSkill projectSkill) {
        em.persist(projectSkill);
    }



}