package wanted.jinyoung.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.jinyoung.member.dto.MemberDto;
import wanted.jinyoung.member.mapper.MemberMapper;
import wanted.jinyoung.member.service.MemberService;

import javax.validation.Valid;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @PostMapping("/signup")
    public ResponseEntity<String> postMember(@RequestBody @Valid MemberDto.Post requestBody){
        memberService.createMember(memberMapper.memberPostToMember(requestBody));

        return ResponseEntity.ok("계정 생성을 완료 했습니다.");
    }
}
