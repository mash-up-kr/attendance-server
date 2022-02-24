package kr.mashup.attendance.service.attendance;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mashup.attendance.domain.attendance.AttendanceEvent;
import kr.mashup.attendance.domain.seminar.Seminar;
import kr.mashup.attendance.dto.attendance.AttendanceEventCreateRequest;
import kr.mashup.attendance.repository.attendance.AttendanceEventRepository;
import kr.mashup.attendance.service.seminar.SeminarService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class AttendanceEventService {

    private final SeminarService seminarService;

    private final AttendanceEventRepository attendanceEventRepository;

    public AttendanceEvent create(Long memberId, AttendanceEventCreateRequest attendanceEventCreateRequest) {
        //TODO AttendanceEvent 생성시 멤버 추가 및 검증 세미나 시간 검증
        Seminar seminar = seminarService.getSeminar(attendanceEventCreateRequest.getSeminarId());
        AttendanceEvent attendanceEvent = AttendanceEvent.of(seminar, attendanceEventCreateRequest.getAttendanceEventType());
        return attendanceEventRepository.save(attendanceEvent);
    }
}
