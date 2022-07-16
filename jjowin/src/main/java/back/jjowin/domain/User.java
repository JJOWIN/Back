package back.jjowin.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class User {

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedData;
    private boolean is_deleted;


}
