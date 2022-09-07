package back.jjowin.dto.contest;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class GetContestListDTO {
    private Long id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String organizer;
    private String info;
    private String imageUrl;
    private String homepage;

    public GetContestListDTO(Long id, String title, LocalDateTime startDate, LocalDateTime endDate, String organizer, String info, String imageUrl, String homepage) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.organizer = organizer;
        this.info = info;
        this.imageUrl = imageUrl;
        this.homepage = homepage;
    }
}
