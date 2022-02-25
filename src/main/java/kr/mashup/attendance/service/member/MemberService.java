package kr.mashup.attendance.service.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import kr.mashup.attendance.domain.member.Member;
import kr.mashup.attendance.dto.member.MemberSignUpRequest;
import kr.mashup.attendance.repository.member.MemberRepository;
import kr.mashup.attendance.service.team.TeamService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamService teamService;

    public Member signUp(MemberSignUpRequest memberSignUpRequest) {
        Assert.notNull(memberSignUpRequest, "'memberSignUpRequest' must not be null");
        // TODO: member validation
        // TODO: password encoding

        Member member = Member.of(
            memberSignUpRequest.getUsername(),
            memberSignUpRequest.getPassword(),
            teamService.getTeam(memberSignUpRequest.getTeamId())
        );
        return memberRepository.save(member);
    }
}
