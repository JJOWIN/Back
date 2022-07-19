package back.jjowin.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
public class User extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String nickname;

    private String email;

    private String password;

    private String job;

    private int job_level;

    private double peer_score;

    private int peer_count;

    private String self_intro;

    private boolean is_cert;

    private boolean is_school;

    private String school_name;

    private boolean is_deleted;

    @OneToMany(mappedBy = "user")
    private List<UserSkill> userSkills;

    @OneToMany(mappedBy = "user")
    private List<Award> awards;

    @OneToMany(mappedBy = "user")
    private List<Like> likes;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "leader")
    private List<Project> leaderProjects;

}
