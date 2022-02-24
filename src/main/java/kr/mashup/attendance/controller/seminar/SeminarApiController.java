package kr.mashup.attendance.controller.seminar;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import kr.mashup.attendance.controller.ApiResponse;
import kr.mashup.attendance.domain.seminar.Seminar;
import kr.mashup.attendance.dto.seminar.SeminarCreateRequest;
import kr.mashup.attendance.dto.seminar.SeminarResponse;
import kr.mashup.attendance.service.seminar.SeminarService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/seminar")
@RestController
public class SeminarApiController {

    private final SeminarService seminarService;

    @ApiOperation("세미나 생성")
    @PostMapping
    public ApiResponse<SeminarResponse> create(
        @RequestBody SeminarCreateRequest seminarCreateRequest
    ) {
        Seminar seminar = seminarService.create(seminarCreateRequest);
        return ApiResponse.success(SeminarResponse.of(seminar));
    }

    @ApiOperation("세미나 리스트 조회")
    @GetMapping
    public ApiResponse<List<SeminarResponse>> getSeminars() {
        List<Seminar> seminars = seminarService.getSeminars();
        List<SeminarResponse> seminarResponses = seminars.stream().map(SeminarResponse::of).collect(Collectors.toList());
        return ApiResponse.success(seminarResponses);
    }

    @ApiOperation("세미나 상세 조회")
    @GetMapping("/{seminarId}")
    public ApiResponse<SeminarResponse> getSeminar(
        @PathVariable Long seminarId
    ) {
        Seminar seminar = seminarService.getSeminar(seminarId);
        return ApiResponse.success(SeminarResponse.of(seminar));
    }
}
