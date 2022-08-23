package back.jjowin.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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

    private int jobLevel;

    private double peerScore;

    private int peerCount;

    private String selfIntro;

    private boolean isCert;

    private boolean isSchool;

    private String schoolName;

    private boolean isDeleted;

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

    public void addAwards(Award award){
        award.setUser(this);
        this.getAwards().add(award);
    }

    public void addLikes(Like like){
        like.setUser(this);
        this.getLikes().add(like);
    }

    public User() {
        userSkills = new ArrayList<>();
        awards = new ArrayList<>();
        likes = new ArrayList<>();
        comments = new ArrayList<>();
        leaderProjects = new ArrayList<>();
    }
}
