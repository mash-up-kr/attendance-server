package kr.mashup.attendance.controller.team;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import kr.mashup.attendance.controller.ApiResponse;
import kr.mashup.attendance.domain.team.Team;
import kr.mashup.attendance.dto.team.TeamCreateRequest;
import kr.mashup.attendance.dto.team.TeamResponse;
import kr.mashup.attendance.service.team.TeamService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/team")
@RestController
public class TeamApiController {

    private final TeamService teamService;

    @ApiOperation("팀 생성")
    @PostMapping
    public ApiResponse<TeamResponse> create(
        @RequestBody TeamCreateRequest teamCreateRequest
    ) {
        Team team = teamService.create(teamCreateRequest);
        return ApiResponse.success(TeamResponse.of(team));
    }
}
