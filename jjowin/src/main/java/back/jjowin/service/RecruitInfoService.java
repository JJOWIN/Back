package back.jjowin.service;

import back.jjowin.domain.RecruitInfo;
import back.jjowin.repository.RecruitInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecruitInfoService {
    private final RecruitInfoRepository recruitInfoRepository;

    @Transactional
    public Long register(RecruitInfo[] recruitInfo){
        for (int i=0; i<recruitInfo.length;i++){
            recruitInfoRepository.save(recruitInfo[i]);
        }
        return recruitInfo[0].getId();
    }

}
