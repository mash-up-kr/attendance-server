package kr.mashup.attendance.repository.season;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mashup.attendance.domain.season.Season;

public interface SeasonRepository extends JpaRepository<Season, Long> {
    Optional<Season> findByNumber(Integer number);
}
