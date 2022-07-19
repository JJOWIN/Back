package back.jjowin.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class UserSkill extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "user_skill_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    private int level;
}
