package kr.mashup.attendance.service.season;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mashup.attendance.domain.season.Season;
import kr.mashup.attendance.dto.season.SeasonCreateRequest;
import kr.mashup.attendance.repository.season.SeasonRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class SeasonService {

    private final SeasonRepository seasonRepository;

    public Season create(SeasonCreateRequest seasonCreateRequest) {
        Season season = Season.from(seasonCreateRequest.getNumber());
        return seasonRepository.save(season);
    }

    @Transactional(readOnly = true)
    public Season getBySeasonNumber(Integer number) {
        return seasonRepository.findByNumber(number).orElseThrow(RuntimeException::new);
    }
}
