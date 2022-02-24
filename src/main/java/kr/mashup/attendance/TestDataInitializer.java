package kr.mashup.attendance;

import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import kr.mashup.attendance.domain.season.Season;
import kr.mashup.attendance.domain.team.Team;
import kr.mashup.attendance.domain.team.TeamType;
import kr.mashup.attendance.repository.season.SeasonRepository;
import kr.mashup.attendance.repository.team.TeamRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile({"local"})
@Slf4j
@Component
@RequiredArgsConstructor
public class TestDataInitializer {

    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
        Season season = createSeason();
        List<Team> teams = createTeam(season);
    }

    private Season createSeason() {
        return seasonRepository.save(Season.from(1));
    }

    private List<Team> createTeam(Season season) {
        teamRepository.save(Team.of("SPRING", season, TeamType.PLATFORM));
        teamRepository.save(Team.of("NODE", season, TeamType.PLATFORM));
        teamRepository.save(Team.of("IOS", season, TeamType.PLATFORM));
        teamRepository.save(Team.of("ANDROID", season, TeamType.PLATFORM));
        teamRepository.save(Team.of("WEB", season, TeamType.PLATFORM));
        teamRepository.save(Team.of("DESIGN", season, TeamType.PLATFORM));
        teamRepository.save(Team.of("씨향", season, TeamType.PROJECT));
        teamRepository.save(Team.of("디깅", season, TeamType.PROJECT));
        return teamRepository.findAll();
    }
}