package back.jjowin.controller;

import back.jjowin.domain.CustomResponseBody;
import back.jjowin.domain.User;
import back.jjowin.domain.UserSkill;
import back.jjowin.service.UserService;
import back.jjowin.service.UserSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserSkillService userSkillService;

    @PostMapping("/users")
    public ResponseEntity signup (@RequestBody User user){
        CustomResponseBody<User> responseBody = new CustomResponseBody<>("회원가입 성공");
        try {
            Long userId = userService.signUp(user);
            List<UserSkill> userSkills = user.getUserSkills();
            for (int i = 0; i < userSkills.size(); i++) {
                userSkills.get(i).setUser(user);
            }
            userSkillService.saveUserSkills(userSkills);

        } catch (IllegalStateException e){
            responseBody.setResultCode(-1);
            responseBody.setResultMsg(e.getMessage());
            return ResponseEntity.ok().body(responseBody);
        } catch (Exception e){
            responseBody.setResultCode(-2);
            responseBody.setResultMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
        }
        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping("/users/login")
    public ResponseEntity login (@RequestBody User user){
        CustomResponseBody<User> responseBody = new CustomResponseBody<>("로그인 성공");
        try {
            User loginUser = userService.login(user);
            responseBody.getList().add(loginUser);
        } catch (IllegalStateException e){
            responseBody.setResultCode(-1);
            responseBody.setResultMsg(e.getMessage());
            return ResponseEntity.ok().body(responseBody);
        } catch (Exception e){
            responseBody.setResultCode(-2);
            responseBody.setResultMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
        }
        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping("/users")
    public ResponseEntity validateDuplicateNickname (@RequestParam(value = "nickname") String nickname){
        CustomResponseBody<User> responseBody = new CustomResponseBody<>("사용가능한 닉네임입니다.");
        try{
            userService.validateDuplicateNickname(nickname);
        } catch (IllegalStateException e){
            responseBody.setResultCode(-1);
            responseBody.setResultMsg(e.getMessage());
            return ResponseEntity.ok().body(responseBody);
        } catch (Exception e){
            responseBody.setResultCode(-2);
            responseBody.setResultMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
        }
        return ResponseEntity.ok().body(responseBody);
    }
}
