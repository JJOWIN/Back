package back.jjowin.controller;

import back.jjowin.domain.BaseResponseBody;
import back.jjowin.domain.CustomResponseBody;
import back.jjowin.domain.User;
import back.jjowin.dto.user.LoginDTO;
import back.jjowin.dto.user.SignupDTO;
import back.jjowin.service.UserService;
import back.jjowin.vo.UserInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
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

    @PostMapping("/user/login")
    public ResponseEntity<BaseResponseBody> login (@RequestBody LoginDTO loginDTO, HttpServletRequest request){
        BaseResponseBody responseBody = new BaseResponseBody("로그인 성공");
        try {
            User loginUser = userService.login(loginDTO);
            if(loginUser == null){
                throw new IllegalStateException("로그인 실패");
            }
            HttpSession session = request.getSession();
            UserInfoVO userInfo = new UserInfoVO(loginUser.getId(), loginUser.getName(), loginUser.getNickname(), loginUser.getIsCert(), loginUser.getIsSchool(), loginUser.getSchoolName(), loginUser.getIsDeleted());
            session.setAttribute("userInfo", userInfo);

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

    @GetMapping("/user/nickname/{nickname}")
    public ResponseEntity<BaseResponseBody> validateDuplicateNickname (@PathVariable(name = "nickname") String nickname){
        BaseResponseBody responseBody = new BaseResponseBody("사용가능한 닉네임입니다.");
        try{
            if(userService.isDuplicateNickname(nickname)){
                responseBody.setResultMsg("닉네임이 중복됩니다");
                responseBody.setResultCode(-3);
                return ResponseEntity.ok().body(responseBody);
            }
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

    @GetMapping("/user/email/{email}")
    public ResponseEntity<BaseResponseBody> validateDuplicateEmail(@PathVariable(name = "email") String email){
        BaseResponseBody responseBody = new BaseResponseBody("사용가능한 이메일입니다.");
        try{
            if(userService.isDuplicateEmail(email)){
                responseBody.setResultMsg("이메일 중복됩니다");
                responseBody.setResultCode(-3);
                return ResponseEntity.ok().body(responseBody);
            }
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<CustomResponseBody<User>> getUserInfo (
             @SessionAttribute(name = "userInfo", required = false) UserInfoVO userInfo
            ,@PathVariable(name = "userId") Long id){
        CustomResponseBody<User> responseBody = new CustomResponseBody<>("");
        try{
            if(userInfo == null){
                responseBody.setResultCode(-10000);
                responseBody.setResultMsg("로그인이 필요한 기능입니다.");
                return ResponseEntity.badRequest().body(responseBody);
            }
            User findUser = userService.getUserInfo(id);
            responseBody.getList().add(findUser);
        } catch (IllegalStateException e){
            responseBody.setResultCode(-1);
            responseBody.setResultMsg(e.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        } catch (Exception e){
            responseBody.setResultCode(-2);
            responseBody.setResultMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
        }
        return ResponseEntity.ok().body(responseBody);
    }

}
