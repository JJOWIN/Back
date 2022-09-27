package back.jjowin.controller;

import back.jjowin.domain.CustomResponseBody;
import back.jjowin.dto.contest.GetContestDetailDTO;
import back.jjowin.dto.contest.GetContestListDTO;
import back.jjowin.service.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContestController {

    private final ContestService contestService;

    @GetMapping("/contest")
    public ResponseEntity<CustomResponseBody<GetContestListDTO>> getContestList(){
        CustomResponseBody<GetContestListDTO> responseBody = new CustomResponseBody<>("성공");
        try{
            responseBody.setList(contestService.getAllContest());
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

    @GetMapping("/contest/{id}")
    public ResponseEntity<CustomResponseBody<GetContestDetailDTO>> getContestDetail(@PathVariable(name = "id") Long id){
        CustomResponseBody<GetContestDetailDTO> responseBody = new CustomResponseBody<>("성공");
        try{
            responseBody.setList(contestService.getContestDetail(id));
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
