package kr.mashup.attendance.repository.attendance;

import kr.mashup.attendance.domain.seminar.Seminar;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.mashup.attendance.domain.attendance.AttendanceEvent;

import java.util.List;

public interface AttendanceEventRepository extends JpaRepository<AttendanceEvent, Long> {
    List<AttendanceEvent> findBySeminar(Seminar seminar);
}
