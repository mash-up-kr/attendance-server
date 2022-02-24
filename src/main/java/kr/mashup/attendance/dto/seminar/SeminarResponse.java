package kr.mashup.attendance.dto.seminar;

import java.time.LocalDateTime;

import kr.mashup.attendance.domain.seminar.Seminar;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SeminarResponse {
    private Long seminarId;
    private Integer season;
    private String title;
    private String theme;
    private LocalDateTime seminarStartedAt;

    public static SeminarResponse of(Seminar seminar) {
        SeminarResponse seminarResponse = new SeminarResponse();
        seminarResponse.seminarId = seminar.getSeminarId();
        seminarResponse.season = seminar.getSeason().getNumber();
        seminarResponse.title = seminar.getTitle();
        seminarResponse.theme = seminar.getTheme();
        seminarResponse.seminarStartedAt = seminar.getSeminarStartedAt();
        return seminarResponse;
    }
}
