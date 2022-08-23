package back.jjowin.repository;

import back.jjowin.domain.UserSkill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserSkillRepository {

    private final EntityManager em;

    /**
     * 유저 기술 저장
     * @param userSkill
     */
    public void save(UserSkill userSkill){
        em.persist(userSkill);
    }

}
