package back.jjowin.service;

import back.jjowin.domain.Contest;
import back.jjowin.dto.contest.GetContestDetailDTO;
import back.jjowin.dto.contest.GetContestListDTO;
import back.jjowin.repository.ContestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContestService {
    private final ContestRepository contestRepository;

    public List<GetContestListDTO> getAllContest(){
        List<GetContestListDTO> result = new ArrayList<>();
        List<Contest> allContest = contestRepository.findAll();
        for (Contest contest : allContest){
            result.add(new GetContestListDTO(contest.getId(), contest.getTitle(), contest.getStartDate(), contest.getEndDate(), contest.getImageUrl(), contest.getHomepage(), contest.getCategory()));
        }
        return result;
    }

    public List<GetContestDetailDTO> getContestDetail(Long id){
        List<GetContestDetailDTO> result = new ArrayList<>();
        Contest findContest = contestRepository.findById(id);
        if(findContest == null){
            throw new IllegalStateException("contest id가 유효하지 않습니다.");
        }
        result.add(new GetContestDetailDTO(findContest));
        return result;
    }
}
