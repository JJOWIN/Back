package back.jjowin.repository;


import back.jjowin.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor    // final 속성을 가진 변수를 포함하는 생성자 자동생성, DI(생성자 의존성 주입)
public class UserRepository {

    private final EntityManager em;

    /**
     * 유저 저장 (Insert & Update)
     */
    public void save(User user) {
        em.persist(user);
    }

    /**
     * 유저 인덱스로 검색
     * @param id 인덱스
     * @return User 클래스
     */
    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    /**
     * 모든 유저 정보 가져오기
     * @return 모든 유저 리스트
     */
    public List<User> findAll() {
        // jpql
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    /**
     * 이메일로 유저 검색
     * @param email
     * @return 유저 리스트
     */
    public List<User> findByEmail(String email) {
        return em.createQuery("select u from User u WHERE u.email = :email AND u.isDeleted = false", User.class)
                .setParameter("email", email)
                .getResultList();
    }

    /**
     * 닉네임으로 유저 검색
     * @param nickname
     * @return
     */
    public List<User> findByNickname(String nickname){
        return em.createQuery("select u from User u WHERE u.nickname = :nickname", User.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }

}