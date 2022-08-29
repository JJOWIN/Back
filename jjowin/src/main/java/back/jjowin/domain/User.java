package back.jjowin.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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

    //전화번호
    private String phone;

    // 메일 수신 동의
    private Boolean isReceiveMail;

    //핸드폰 본인인증 여부
    private Boolean isCert;

    //학교 인증 여부
    private Boolean isSchool;

    //학교 인증 이메일
    private String schoolEmail;

    //학교 이름
    private String schoolName;

    private Boolean isDeleted;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<UserSkill> userSkills;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Award> awards;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Like> likes;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Comment> comments;

    @OneToMany(mappedBy = "leader")
    @JsonManagedReference
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
        isReceiveMail = false;
        isCert = false;
        isDeleted = false;
        isSchool = false;
    }
}
