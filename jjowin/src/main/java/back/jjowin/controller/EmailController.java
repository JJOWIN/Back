package back.jjowin.controller;

import back.jjowin.domain.BaseResponseBody;
import back.jjowin.dto.email.RequestEmailTokenDTO;
import back.jjowin.service.EmailTokenService;
import back.jjowin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailTokenService emailTokenService;
    @GetMapping("/confirm-mail")
    public ResponseEntity<BaseResponseBody> confirmEmail(@Valid @RequestParam(name = "token") String token){
        BaseResponseBody responseBody = new BaseResponseBody("이메일 인증 성공");
        try {
            if(!emailTokenService.verifyEmail(token)){
                throw new IllegalStateException("이메일 인증 실패");
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

    @PostMapping("/confirm-mail")
    public ResponseEntity<BaseResponseBody> requestEmailToken(@RequestBody RequestEmailTokenDTO requestEmailTokenDTO){
        BaseResponseBody responseBody = new BaseResponseBody("인증 이메일 전송 성공");
        try {
            String token = emailTokenService.createEmailToken(requestEmailTokenDTO);
            if(token.isBlank()){
                throw new IllegalStateException("인증 이메일 전송 실패");
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
}
