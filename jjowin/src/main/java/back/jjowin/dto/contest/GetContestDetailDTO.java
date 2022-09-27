package back.jjowin.dto.contest;

import back.jjowin.domain.Contest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
public class GetContestDetailDTO {
    private Long id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String category;
    private String organizer;
    private String info;
    private String imageUrl;
    private String homepage;
    private Boolean isProgress;

    public GetContestDetailDTO(Contest contest){
        this.id = contest.getId();
        this.title = contest.getTitle();
        this.startDate = contest.getStartDate();
        this.endDate = contest.getEndDate();
        this.category = contest.getCategory();
        this.organizer = contest.getOrganizer();
        this.info = contest.getInfo();
        this.imageUrl = contest.getImageUrl();
        this.homepage = contest.getHomepage();
        this.isProgress = LocalDateTime.now().isAfter(contest.getStartDate()) && LocalDateTime.now().isBefore(contest.getEndDate());
    }
}
