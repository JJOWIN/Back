package back.jjowin.repository;


import back.jjowin.domain.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectRepository {

    private final EntityManager em;

    /**
     * 프로젝트 저장 (Insert & Update)
     */
    public void save(Project project) {
        em.persist(project);
    }

    /**
     * 프로젝트 인덱스로 검색
     *
     * @param id 인덱스
     * @return Project 클래스
     */
    public Project findOne(Long id) {
        return em.find(Project.class, id);
    }

    /**
     * 모든 프로젝트 정보 가져오기
     *
     * @return 모든 프로젝트 리스트
     */
    public List<Project> findAll() {
        // jpql
        return em.createQuery("select u from User u", Project.class)
                .getResultList();
    }

    /**
     * 이름으로 프로젝트 검색
     *
     * @param name
     * @return 프로젝트 리스트
     */
    public List<Project> findByName(String name) {
        return em.createQuery("select u from Project u WHERE u.name = :name", Project.class)
                .setParameter("name", name)
                .getResultList();
    }

}