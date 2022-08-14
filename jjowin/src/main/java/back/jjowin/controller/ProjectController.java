package back.jjowin.controller;

import back.jjowin.domain.CustomResponseBody;
import back.jjowin.DTO.ProjectCreateDTO;
import back.jjowin.service.ProjectService;
import back.jjowin.service.ProjectSkillService;
import back.jjowin.service.RecruitInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectSkillService projectSkillService;
    private final RecruitInfoService recruitInfoService;


    @PostMapping("/projects")
    public ResponseEntity register (@RequestBody ProjectCreateDTO model){
        CustomResponseBody<ProjectCreateDTO> responseBody = new CustomResponseBody<>("프로젝트 등록 성공2");
        try {
            projectService.register(model.getProject());
            projectSkillService.register(model.getProjectSkill());
            recruitInfoService.register(model.getRecruitInfo());

        } catch (RuntimeException re){
            responseBody.setResultCode(-1);
            responseBody.setResultMsg(re.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        } catch (Exception e){
            responseBody.setResultCode(-2);
            responseBody.setResultMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
        }

        return ResponseEntity.ok().body(responseBody);
    }
}
