package kr.mashup.attendance.repository.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mashup.attendance.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}
