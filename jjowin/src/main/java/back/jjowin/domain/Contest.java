package back.jjowin.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Contest {

    @Id
    @GeneratedValue
    @Column(name = "contest_id")
    private Long id;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String organizer;

    @Column(length = 1500)
    private String info;

    private String imageUrl;

}
