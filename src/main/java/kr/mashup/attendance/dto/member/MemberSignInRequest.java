package kr.mashup.attendance.dto.member;

import lombok.Data;

@Data
public class MemberSignInRequest {
    private String username;
    private String password;
}
