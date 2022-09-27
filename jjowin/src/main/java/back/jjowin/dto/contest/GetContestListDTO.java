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
    private String imageUrl;
    private String homepage;
    private String category;
    private Boolean isProgress;

    public GetContestListDTO(Long id, String title, LocalDateTime startDate, LocalDateTime endDate, String imageUrl, String homepage, String category) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.homepage = homepage;
        this.category = category;
        this.isProgress = LocalDateTime.now().isAfter(startDate) && LocalDateTime.now().isBefore(endDate);
    }
}
