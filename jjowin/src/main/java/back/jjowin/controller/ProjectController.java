package back.jjowin.controller;

import back.jjowin.domain.BaseResponseBody;
import back.jjowin.domain.CustomResponseBody;
import back.jjowin.domain.User;
import back.jjowin.dto.project.AllToyProjectListDTO;
import back.jjowin.dto.project.ProjectCreateDTO;
import back.jjowin.repository.UserRepository;
import back.jjowin.service.ProjectService;
import back.jjowin.service.ProjectSkillService;
import back.jjowin.service.RecruitInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectSkillService projectSkillService;
    private final RecruitInfoService recruitInfoService;
    private final UserRepository userRepository;
    /**
     * 프로젝트 등록
     * @param projectCreateDTO
     * @return
     */
    @PostMapping("/projects")
    public ResponseEntity<BaseResponseBody> register (
            @RequestBody ProjectCreateDTO projectCreateDTO,
            @CookieValue(name = "UserInfo", required = false) String cookieUserId){
        BaseResponseBody responseBody = new BaseResponseBody("프로젝트 등록 성공");
        User findUser = null;
        try {
            if(cookieUserId.isEmpty()){
                responseBody.setResultCode(-10000);
                responseBody.setResultMsg("로그인이 필요한 기능입니다.");
                return ResponseEntity.badRequest().body(responseBody);
            }
            findUser = userRepository.findOne(Long.parseLong(cookieUserId));
            if(findUser == null){
                responseBody.setResultCode(-10001);
                responseBody.setResultMsg("유효하지 않은 사용자입니다.");
            }
            projectCreateDTO.setLeader(Long.parseLong(cookieUserId));
            if(projectCreateDTO.getJoinSchool()){
                if(!findUser.getIsSchool()){
                    responseBody.setResultCode(-10002);
                    responseBody.setResultMsg("학교 인증이 필요합니다.");
                }
                projectCreateDTO.setSchoolName(findUser.getSchoolName());
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

    /**
     * 모집중인 모든 토이 프로젝트 리스트 불러오기
     * @return
     */
    @GetMapping("/projects/allToyProject")
    public ResponseEntity<CustomResponseBody<AllToyProjectListDTO>> allToyProject() {
        CustomResponseBody<AllToyProjectListDTO> responseBody = new CustomResponseBody<>("모든 토이프로젝트 리스트 불러오기 성공");
        try{
            List<AllToyProjectListDTO> allToyProjects = projectService.findAllToyProjects();
            responseBody.setList(allToyProjects);

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
