package kr.mashup.attendance.repository.team;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mashup.attendance.domain.team.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
