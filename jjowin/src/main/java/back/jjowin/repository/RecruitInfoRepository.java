package back.jjowin.repository;


import back.jjowin.domain.RecruitInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecruitInfoRepository {

    private final EntityManager em;

    /**
     * 모집세부직무 저장 (Insert & Update)
     */
    public void save(RecruitInfo recruitInfo) {
        em.persist(recruitInfo);
    }



}