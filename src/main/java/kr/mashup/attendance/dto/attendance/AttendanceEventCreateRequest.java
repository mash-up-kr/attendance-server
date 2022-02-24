package kr.mashup.attendance.dto.attendance;

import kr.mashup.attendance.domain.attendance.AttendanceEventType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AttendanceEventCreateRequest {
    private Long seminarId;
    //TODO AttendanceEventType 을 받을 것인가 seminarId만 받아서 서버에서 조회해서 판단해 사용 할 것인가?
    private AttendanceEventType attendanceEventType;
}
