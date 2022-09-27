package back.jjowin.dto.project;

import back.jjowin.domain.ProjectStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class AllToyProjectListDTO {

    private Long id;
    private String name;
    private ProjectStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private String image;
}
