package back.jjowin.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter
public class RecruitInfo {

    @Id @GeneratedValue
    @Column(name = "recruit_info_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    private String job;

    private int count;

    @ColumnDefault("0")
    private int completeCount;
}
