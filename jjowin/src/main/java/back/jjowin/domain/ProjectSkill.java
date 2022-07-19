package back.jjowin.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ProjectSkill extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "project_skill_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    private String name;
}
