package kr.mashup.attendance.dto.team;

import kr.mashup.attendance.domain.team.Team;
import kr.mashup.attendance.domain.team.TeamType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TeamResponse {
    private Integer season;
    private String name;
    private TeamType type;

    public static TeamResponse of(Team team) {
        TeamResponse teamResponse = new TeamResponse();
        teamResponse.season = team.getSeason().getNumber();
        teamResponse.name = team.getName();
        teamResponse.type = team.getType();
        return teamResponse;
    }
}
