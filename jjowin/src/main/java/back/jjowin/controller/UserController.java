package back.jjowin.controller;

import back.jjowin.domain.BaseResponseBody;
import back.jjowin.domain.CustomResponseBody;
import back.jjowin.domain.User;
import back.jjowin.dto.LoginDTO;
import back.jjowin.dto.SignupDTO;
import back.jjowin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<BaseResponseBody> signup (@RequestBody SignupDTO signupDTO){
        BaseResponseBody responseBody = new BaseResponseBody("회원가입 성공");
        try {
            userService.signUp(signupDTO);
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
    public ResponseEntity<CustomResponseBody<User>> login (@RequestBody LoginDTO loginDTO){
        CustomResponseBody<User> responseBody = new CustomResponseBody<>("로그인 성공");
        try {
            User loginUser = userService.login(loginDTO);
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

    @GetMapping("/user/nickname")
    public ResponseEntity<CustomResponseBody<User>> validateDuplicateNickname (@RequestParam(value = "nickname") String nickname){
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

    @GetMapping("/user")
    public ResponseEntity<CustomResponseBody<User>> getUserInfo (@RequestParam(value = "id") Long id){
        CustomResponseBody<User> responseBody = new CustomResponseBody<>("");
        try{
            User findUser = userService.getUserInfo(id);
            responseBody.getList().add(findUser);
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
