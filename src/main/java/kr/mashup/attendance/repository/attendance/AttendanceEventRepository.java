package kr.mashup.attendance.repository.attendance;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mashup.attendance.domain.attendance.AttendanceEvent;

public interface AttendanceEventRepository extends JpaRepository<AttendanceEvent, Long> {
}
