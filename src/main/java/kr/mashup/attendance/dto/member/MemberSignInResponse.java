package kr.mashup.attendance.dto.member;

import kr.mashup.attendance.domain.member.Member;
import lombok.Getter;

@Getter
public class MemberSignInResponse {
    private String accessToken;
    private Member member;

    public static MemberSignInResponse of (Member member) {
        MemberSignInResponse memberSignInResponse = new MemberSignInResponse();
        memberSignInResponse.accessToken = null; // TODO: tokenService 생성 후 변경
        memberSignInResponse.member = member;
        return memberSignInResponse;
    }
}
