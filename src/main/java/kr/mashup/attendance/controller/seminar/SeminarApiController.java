package kr.mashup.attendance.controller.seminar;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
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
    public SeminarResponse create(
        @RequestBody SeminarCreateRequest seminarCreateRequest
    ) {
        Seminar seminar = seminarService.create(seminarCreateRequest);
        return SeminarResponse.of(seminar);
    }
}
