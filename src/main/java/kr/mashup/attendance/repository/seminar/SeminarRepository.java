package kr.mashup.attendance.repository.seminar;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mashup.attendance.domain.seminar.Seminar;

public interface SeminarRepository extends JpaRepository<Seminar, Long> {
}
