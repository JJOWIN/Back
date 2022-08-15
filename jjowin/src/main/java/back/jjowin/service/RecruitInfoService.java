package back.jjowin.service;

import back.jjowin.domain.Project;
import back.jjowin.domain.RecruitInfo;
import back.jjowin.repository.ProjectRepository;
import back.jjowin.repository.RecruitInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecruitInfoService {
    private final RecruitInfoRepository recruitInfoRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public void register(RecruitInfo[] recruitInfos, Long projectId){

        Project project = projectRepository.findOne(projectId);

        for (int i=0; i<recruitInfos.length;i++){
            recruitInfos[i].setProject(project);
            recruitInfoRepository.save(recruitInfos[i]);
        }
    }

}
