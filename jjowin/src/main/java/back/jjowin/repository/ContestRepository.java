package back.jjowin.repository;

import back.jjowin.domain.Contest;
import back.jjowin.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContestRepository {
    private final EntityManager em;

    public Contest findOne(Long id) {
        return em.find(Contest.class, id);
    }

    public List<Contest> findAll() {
        return em.createQuery("SELECT C FROM Contest C", Contest.class)
                .getResultList();
    }
    public Contest findById(Long id){
        return em.find(Contest.class, id);
    }
}
