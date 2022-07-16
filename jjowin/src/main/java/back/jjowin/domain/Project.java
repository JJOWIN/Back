package back.jjowin.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Project {

    @Id @GeneratedValue
    @Column(name = "project_id")
    private Long id;

    private String name;

    private String category;

    private String contest_url;

    private String school_name;

    private String description;

    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date start_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date end_date;

    private int like_count;
}