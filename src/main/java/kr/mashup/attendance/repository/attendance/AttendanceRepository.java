package kr.mashup.attendance.repository.attendance;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mashup.attendance.domain.attendance.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
