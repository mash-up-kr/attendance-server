package kr.mashup.attendance.dto.member;

import lombok.Getter;

@Getter
public class MemberSignUpRequest {
    private String username;
    private String password;
    private Long teamId;
}
