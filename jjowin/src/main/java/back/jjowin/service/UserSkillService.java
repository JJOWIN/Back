package back.jjowin.service;

import back.jjowin.domain.UserSkill;
import back.jjowin.repository.UserRepository;
import back.jjowin.repository.UserSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserSkillService {
    private final UserSkillRepository userSkillRepository;

    public void saveUserSkills(List<UserSkill> userSkills){
        for (UserSkill userSkill: userSkills){
            userSkillRepository.save(userSkill);
        }
    }


}
