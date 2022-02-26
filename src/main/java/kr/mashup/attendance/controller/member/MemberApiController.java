package kr.mashup.attendance.controller.member;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import kr.mashup.attendance.controller.ApiResponse;
import kr.mashup.attendance.domain.member.Member;
import kr.mashup.attendance.dto.member.MemberSignInRequest;
import kr.mashup.attendance.dto.member.MemberSignInResponse;
import kr.mashup.attendance.dto.member.MemberSignUpRequest;
import kr.mashup.attendance.service.member.MemberService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @ApiOperation("회원가입")
    @PostMapping("/signUp")
    public ApiResponse signUp(
        @RequestBody MemberSignUpRequest memberSignUpRequest
    ) {
        Member member = memberService.signUp(memberSignUpRequest);
        return ApiResponse.success();
    }

    @ApiOperation("로그인")
    @PostMapping("/signIn")
    public ApiResponse<MemberSignInResponse> login(
        @RequestBody MemberSignInRequest memberSignInRequest
    ) {
        Member member = memberService.signIn(memberSignInRequest);
        return ApiResponse.success(MemberSignInResponse.of(member));
    }
}
