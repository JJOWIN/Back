package back.jjowin.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contest_id")
    private Long id;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String organizer;

    @Column(length = 5000)
    private String info;
    @Column(length = 800)
    private String imageUrl;

    private String homepage;

    @OneToMany(mappedBy = "contestNo")
    private List<Project> projects = new ArrayList<>();

}
