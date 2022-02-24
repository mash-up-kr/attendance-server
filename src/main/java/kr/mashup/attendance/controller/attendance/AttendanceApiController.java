package kr.mashup.attendance.controller.attendance;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import kr.mashup.attendance.controller.ApiResponse;
import kr.mashup.attendance.domain.attendance.AttendanceEvent;
import kr.mashup.attendance.dto.attendance.AttendanceEventCreateRequest;
import kr.mashup.attendance.dto.attendance.AttendanceEventResponse;
import kr.mashup.attendance.service.attendance.AttendanceEventService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/attendance")
@RestController
public class AttendanceApiController {

    private final AttendanceEventService attendanceEventService;

    @ApiOperation("출석 체크")
    @PostMapping
    public ApiResponse<AttendanceEventResponse> create(
        @RequestBody AttendanceEventCreateRequest attendanceEventCreateRequest
    ) {
        Long memberId = 0L; // 임시 처리
        AttendanceEvent attendanceEvent = attendanceEventService.create(memberId, attendanceEventCreateRequest);
        return ApiResponse.success(AttendanceEventResponse.of(attendanceEvent));
    }
}
