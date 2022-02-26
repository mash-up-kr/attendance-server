package kr.mashup.attendance.repository.attendance;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mashup.attendance.domain.attendance.Attendance;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findBySeminar_seminarId(Long seminarId);
}
