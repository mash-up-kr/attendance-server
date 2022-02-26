package kr.mashup.attendance.service.attendance;

import kr.mashup.attendance.domain.attendance.Attendance;
import kr.mashup.attendance.domain.attendance.AttendanceEvent;
import kr.mashup.attendance.domain.attendance.AttendanceType;
import kr.mashup.attendance.domain.member.Member;
import kr.mashup.attendance.domain.seminar.Seminar;
import kr.mashup.attendance.repository.attendance.AttendanceRepository;
import kr.mashup.attendance.service.seminar.SeminarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class AttendanceService {

    private final SeminarService seminarService;
    private final AttendanceEventService attendanceEventService;

    private final AttendanceRepository attendanceRepository;

    public Boolean attendanceCheck(Long seminarId) {
        Seminar seminar = seminarService.getSeminar(seminarId);
        Map<Member, List<AttendanceEvent>> attendanceEventMap = attendanceEventService.getBySeminar(seminar)
                .stream()
                .collect(Collectors.groupingBy(AttendanceEvent::getMember));

        List<Attendance> attendances = new ArrayList<>();

        //TODO 출첵 로직 임시로 생성, 출첵 기획 확정 후 이 부분  새로 작성 해야 함
        attendanceEventMap.forEach((member, attendanceEventList) -> {
            if (attendanceEventList.size() == 2) {
                attendances.add(Attendance.of(seminar, member, AttendanceType.PRESENT));
            } else if (attendanceEventList.size() == 1) {
                attendances.add(Attendance.of(seminar, member, AttendanceType.LATE));
            } else {
                attendances.add(Attendance.of(seminar, member, AttendanceType.ABSENT));
            }
        });

        attendanceRepository.saveAll(attendances);
        return true;
    }

    public List<Attendance> getAttendancesBySeminarId(Long seminarId) {
        return attendanceRepository.findBySeminar_seminarId(seminarId);
    }
}
