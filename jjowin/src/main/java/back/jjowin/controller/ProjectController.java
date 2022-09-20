package back.jjowin.controller;

import back.jjowin.domain.BaseResponseBody;
import back.jjowin.dto.project.ProjectCreateDTO;
import back.jjowin.service.ProjectService;
import back.jjowin.service.ProjectSkillService;
import back.jjowin.service.RecruitInfoService;
import back.jjowin.vo.UserInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectSkillService projectSkillService;
    private final RecruitInfoService recruitInfoService;


    @PostMapping("/projects")
    public ResponseEntity<BaseResponseBody> register (
            @RequestBody ProjectCreateDTO projectCreateDTO,
            @SessionAttribute(name = "userInfo", required = false) UserInfoVO userInfo){
        BaseResponseBody responseBody = new BaseResponseBody("프로젝트 등록 성공");
        try {
            if(userInfo == null){
                responseBody.setResultCode(-10000);
                responseBody.setResultMsg("로그인이 필요한 기능입니다.");
                return ResponseEntity.badRequest().body(responseBody);
            }
            projectCreateDTO.setLeader(userInfo.getId());
            if (projectCreateDTO.getJoinSchool() == true && userInfo.isSchool() == true) {
                projectCreateDTO.setSchoolName(userInfo.getSchoolName());
            }

            projectService.register(projectCreateDTO);

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
