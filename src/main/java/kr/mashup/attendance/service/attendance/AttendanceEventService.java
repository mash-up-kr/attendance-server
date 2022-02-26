package kr.mashup.attendance.service.attendance;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mashup.attendance.domain.attendance.AttendanceEvent;
import kr.mashup.attendance.domain.attendance.AttendanceEventType;
import kr.mashup.attendance.domain.seminar.Seminar;
import kr.mashup.attendance.dto.attendance.AttendanceEventCreateRequest;
import kr.mashup.attendance.exception.attendance.AttendanceCheckTimeInvalidException;
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
        LocalDateTime now = LocalDateTime.now();
        Seminar seminar = seminarService.getSeminar(attendanceEventCreateRequest.getSeminarId());
        AttendanceEventType attendanceEventType = decideAttendanceEventType(now, seminar);
        AttendanceEvent attendanceEvent = AttendanceEvent.of(seminar, attendanceEventType);
        return attendanceEventRepository.save(attendanceEvent);
    }

    public List<AttendanceEvent> getBySeminar(Seminar seminar) {
        return attendanceEventRepository.findBySeminar(seminar);
    }

    private AttendanceEventType decideAttendanceEventType(LocalDateTime now, Seminar seminar) {
        if (!now.isBefore(seminar.getFirstAttendanceCheckedAt()) &&
            !now.isAfter(seminar.getFirstAttendanceCheckExpiredTime())) {
            return AttendanceEventType.FIRST;
        } else if (!now.isBefore(seminar.getSecondAttendanceCheckedAt()) &&
            !now.isAfter(seminar.getSecondAttendanceCheckExpiredTime())) {
            return AttendanceEventType.SECOND;
        }
        throw new AttendanceCheckTimeInvalidException();
    }
}
