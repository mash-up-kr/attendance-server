package kr.mashup.attendance.service.seminar;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mashup.attendance.domain.season.Season;
import kr.mashup.attendance.domain.seminar.Seminar;
import kr.mashup.attendance.dto.seminar.SeminarCreateRequest;
import kr.mashup.attendance.repository.seminar.SeminarRepository;
import kr.mashup.attendance.service.season.SeasonService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class SeminarService {

    private final SeasonService seasonService;

    private final SeminarRepository seminarRepository;

    public Seminar create(SeminarCreateRequest seminarCreateRequest) {
        //TODO : 현재시간보다 빠른 시간으로 세미나 만들지 못하게
        Season season = seasonService.getBySeasonNumber(seminarCreateRequest.getSeason());
        Seminar seminar = Seminar.of(
            season,
            seminarCreateRequest.getTitle(),
            seminarCreateRequest.getTheme(),
            seminarCreateRequest.getSeminarStartedAt()
        );
        return seminarRepository.save(seminar);
    }

    @Transactional(readOnly = true)
    public List<Seminar> getSeminars() {
        //TODO 세미나 정렬 조건 및 페이징 적용
        return seminarRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Seminar getSeminar(Long seminarId) {
        return seminarRepository.findById(seminarId).orElseThrow(RuntimeException::new);
    }
}
