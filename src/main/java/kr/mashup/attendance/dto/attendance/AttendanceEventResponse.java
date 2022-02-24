package kr.mashup.attendance.dto.attendance;

import kr.mashup.attendance.domain.attendance.AttendanceEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AttendanceEventResponse {
    private Long attendanceEventId;

    public static AttendanceEventResponse of (AttendanceEvent attendanceEvent) {
        AttendanceEventResponse attendanceEventResponse = new AttendanceEventResponse();
        attendanceEventResponse.attendanceEventId = attendanceEvent.getAttendanceEventId();
        return attendanceEventResponse;
    }
}
