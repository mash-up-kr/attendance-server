package kr.mashup.attendance.dto.team;

import kr.mashup.attendance.domain.team.TeamType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TeamCreateRequest {
    private Integer season;
    private String name;
    private TeamType type;
}
