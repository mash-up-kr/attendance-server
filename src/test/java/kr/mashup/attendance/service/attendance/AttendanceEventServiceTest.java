package kr.mashup.attendance.service.attendance;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import kr.mashup.attendance.domain.attendance.AttendanceEventType;
import kr.mashup.attendance.domain.season.Season;
import kr.mashup.attendance.domain.seminar.Seminar;
import kr.mashup.attendance.exception.attendance.AttendanceCheckTimeInvalidException;
import kr.mashup.attendance.repository.season.SeasonRepository;
import kr.mashup.attendance.repository.seminar.SeminarRepository;

@Transactional
@SpringBootTest
class AttendanceEventServiceTest {

    private static final LocalDateTime SEMINAR_STARTED_AT = LocalDateTime.of(2022, 2, 26, 3, 0);
    private static final LocalDateTime FIRST_ATTENDANCE_CHECKED_AT = LocalDateTime.of(2022, 2, 26, 3, 0);
    private static final LocalDateTime SECOND_ATTENDANCE_CHECKED_AT = LocalDateTime.of(2022, 2, 26, 4, 30);

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    SeminarRepository seminarRepository;

    @Autowired
    AttendanceEventService attendanceEventService;

    @DisplayName("세미나 1부 출석체크 유효기간 내에 요청시 AttendanceEventType 은 FIRST 가 된다.")
    @Test
    public void firstAttendanceCheck() {
        Season season = seasonRepository.save(Season.from(1));
        Seminar seminar = seminarRepository.save(
            Seminar.of(
                season,
                "최고의 세미나",
                "주제는 없다!",
                SEMINAR_STARTED_AT,
                FIRST_ATTENDANCE_CHECKED_AT,
                SECOND_ATTENDANCE_CHECKED_AT
            )
        );

        LocalDateTime requestTime = LocalDateTime.of(2022, 2, 26, 3, 1);
        AttendanceEventType type = ReflectionTestUtils.invokeMethod(attendanceEventService, "decideAttendanceEventType",
            requestTime, seminar);

        assertEquals(AttendanceEventType.FIRST, type);
    }

    @DisplayName("세미나 2부 출석체크 유효기간 내에 요청시 AttendanceEventType 은 SECOND 가 된다.")
    @Test
    public void secondAttendanceCheck() {
        Season season = seasonRepository.save(Season.from(1));
        Seminar seminar = seminarRepository.save(
            Seminar.of(
                season,
                "최고의 세미나",
                "주제는 없다!",
                SEMINAR_STARTED_AT,
                FIRST_ATTENDANCE_CHECKED_AT,
                SECOND_ATTENDANCE_CHECKED_AT
            )
        );

        LocalDateTime requestTime = LocalDateTime.of(2022, 2, 26, 4, 35);
        AttendanceEventType type = ReflectionTestUtils.invokeMethod(attendanceEventService, "decideAttendanceEventType",
            requestTime, seminar);

        assertEquals(AttendanceEventType.SECOND, type);
    }

    @DisplayName("세미나 출석체크 유효기간 이외에 요청시 AttendanceCheckTimeInvalidException 을 던진다.")
    @Test
    public void invalidAttendanceCheck() {
        Season season = seasonRepository.save(Season.from(1));
        Seminar seminar = seminarRepository.save(
            Seminar.of(
                season,
                "최고의 세미나",
                "주제는 없다!",
                SEMINAR_STARTED_AT,
                FIRST_ATTENDANCE_CHECKED_AT,
                SECOND_ATTENDANCE_CHECKED_AT
            )
        );

        LocalDateTime requestTime = LocalDateTime.of(2022, 2, 26, 4, 20);

        assertThrows(
            AttendanceCheckTimeInvalidException.class,
            () -> ReflectionTestUtils.invokeMethod(
                attendanceEventService,
                "decideAttendanceEventType",
                requestTime,
                seminar
            )
        );
    }
}