package kr.mashup.attendance.service.team;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mashup.attendance.domain.season.Season;
import kr.mashup.attendance.domain.team.Team;
import kr.mashup.attendance.dto.team.TeamCreateRequest;
import kr.mashup.attendance.exception.team.TeamNotFoundException;
import kr.mashup.attendance.repository.team.TeamRepository;
import kr.mashup.attendance.service.season.SeasonService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class TeamService {

    private final SeasonService seasonService;

    private final TeamRepository teamRepository;

    public Team create(TeamCreateRequest teamCreateRequest) {
        Season season = seasonService.getBySeasonNumber(teamCreateRequest.getSeason());
        Team team = Team.of(teamCreateRequest.getName(), season, teamCreateRequest.getType());
        return teamRepository.save(team);
    }

    public Team getTeam(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(TeamNotFoundException::new);
    }
}
