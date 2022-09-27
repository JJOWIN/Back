package back.jjowin.repository;

import back.jjowin.domain.EmailToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmailTokenRepository {
    private final EntityManager em;

    public void save(EmailToken emailToken){
        em.persist(emailToken);
    }

    public Optional<EmailToken> findByIdAndExpirationDateAfterAndIsExpired(String id, LocalDateTime now, Boolean isExpired){
        return Optional.ofNullable(em.createQuery("SELECT et " +
                                "FROM EmailToken et WHERE et.id = :id AND et.expirationDate > :now AND et.isExpired = :isExpired " +
                                "ORDER BY et.expirationDate DESC"
                                , EmailToken.class)
                                .setParameter("id", id)
                                .setParameter("now", now)
                                .setParameter("isExpired", isExpired)
                                .getSingleResult());
    }

}
