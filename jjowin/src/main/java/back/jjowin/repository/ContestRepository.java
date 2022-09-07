package back.jjowin.repository;

import back.jjowin.domain.Contest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContestRepository {
    private final EntityManager em;

    public List<Contest> findAll() {
        return em.createQuery("SELECT C FROM Contest C", Contest.class)
                .getResultList();
    }
}
